package io.renren.config;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.ShiroService;
import io.renren.modules.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangyulong
 * @date 2018/4/19
 */
@Slf4j
@Component
public class MyShiroCasRealm extends CasRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyShiroCasRealm.class);

    @Autowired
    private ShiroService shiroService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysUserDao sysUserDao;
    @Value("${spring.cas.server.url}")
    String casServerUrl;
    @Value("${spring.cas.client.filter.url}")
    String casClientFilterUrl;

    @PostConstruct
    public void initProperty() {
//      setDefaultRoles("ROLE_USER");
        super.setCasServerUrlPrefix(casServerUrl);
        // 客户端回调地址
        super.setCasService(casClientFilterUrl);
    }

    /**
     * 权限认证，为当前登录的Subject授予角色和权限
     *
     * @see ：本例中该方法的调用时机为需授权资源被访问时
     * @see ：并且每次访问需授权资源时都会执行该方法中的逻辑，这表明本例中默认并未启用AuthorizationCache
     * @see ：如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SysUserEntity user = (SysUserEntity)principalCollection.getPrimaryPrincipal();
        Long userId = user.getUserId();

        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {

        CasToken casToken = (CasToken) token;
        if (token == null) {
            return null;
        }
        String ticket = (String) casToken.getCredentials();
        if (!StringUtils.hasText(ticket)) {
            return null;
        }
        TicketValidator ticketValidator = ensureTicketValidator();
        try {
            Assertion casAssertion = ticketValidator.validate(ticket, getCasService());
            AttributePrincipal casPrincipal = casAssertion.getPrincipal();
            Map userInfo = casPrincipal.getAttributes();
            String uName = casPrincipal.getName();
            log.info("Validate ticket : {} in CAS server : {} to retrieve user : {}", new Object[]{ticket, getCasServerUrlPrefix(), uName});
            casToken.setUserId(uName);
            String rememberMeAttributeName = getRememberMeAttributeName();
            String rememberMeStringValue = (String) userInfo.get(rememberMeAttributeName);
            boolean isRemembered = rememberMeStringValue != null && Boolean.parseBoolean(rememberMeStringValue);
            if (isRemembered) {
                casToken.setRememberMe(true);
            }
            List principals = Arrays.asList(new Object[]{uName, userInfo});
            PrincipalCollection principalCollection = new SimplePrincipalCollection(principals, getName());

            SysUserEntity user = sysUserService.queryByUserName(uName);
            if(user!=null && user.getStatus() == 0){
                throw new LockedAccountException("账号已被锁定,请联系管理员");
            }
            SecurityUtils.getSubject().getSession().setAttribute("user", user);

            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, ticket, getName());
            return  info;
        } catch (TicketValidationException e) {

            throw new CasAuthenticationException((new StringBuilder())
                    .append("Unable to validate ticket [").append(ticket)
                    .append("]").toString(), e);

        }
    }
}
