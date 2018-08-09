package io.renren.controller;

import io.renren.modules.sys.entity.SysUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * @author zhangyulong
 * @date 2018/4/21
 */
@Slf4j
@Controller
public class CasController {


    /**
     * 登录
     *
     * @param request  请求
     * @param response 响应
     * @return String 页面
     */
    @RequestMapping(value = "/casCB")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        log.info("login...");

        Subject subject = SecurityUtils.getSubject();
        Principal principal = (Principal) subject.getPrincipal();
        // 如果已经登录，则跳转到管理首页
        if (principal != null) {
            log.info("登陆成功，进入主页");
            return "redirect:/index.html"; // 首页
        }
        SysUserEntity sysUserEntity = (SysUserEntity) request.getAttribute("user");

        log.info("sysUserEntity:{}", sysUserEntity);
        log.info("登陆失败");
        return "redirect:/index.html";
    }
}
