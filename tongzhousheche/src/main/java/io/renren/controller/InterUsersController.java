package io.renren.controller;

import com.tceasy.common.utils.json.JsonUtil;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;

/**
 * 用户接口
 *
 * @author zhangqiang
 * @date 2018/4/27
 */
@Slf4j
@RestController
public class InterUsersController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 增加用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/cas/AddUser")
    public R addUser(@RequestBody String param) {
        try {
            String prevURL = "";
            String decodeURL = param;
            while (!prevURL.equals(decodeURL)) {
                prevURL = decodeURL;
                decodeURL = URLDecoder.decode(prevURL, "UTF-8");
                String reData = URLDecoder.decode(decodeURL, "UTF-8");
                log.info(reData);
                Map resultData = JsonUtil.toMap(reData.split("=")[1]);
                SysUserEntity sysUserEntity = new SysUserEntity();
                sysUserEntity.setDdid((Integer) resultData.get("id"));
                sysUserEntity.setDeptId((String) resultData.get("deptId"));
                sysUserEntity.setDeptNm((String) resultData.get("deptName"));

                if ((String) resultData.get("userName") != null) {
                    sysUserEntity.setUsername((String) resultData.get("userName"));
                } else {
                    sysUserEntity.setUsername((String) resultData.get("mobile") + "123");
                }
                sysUserEntity.setMobile((String) resultData.get("mobile"));

                if ((String) resultData.get("password") != null) {
                    sysUserEntity.setPassword((String) resultData.get("password"));
                } else {
                    sysUserEntity.setPassword("123456");
                }
                sysUserEntity.setIdCard((String) resultData.get("idCard"));
                sysUserEntity.setStatus(1);
                sysUserEntity.setDeptIdCard((String) resultData.get("deptIdCard"));
                if ((String) resultData.get("email") != null) {
                    sysUserEntity.setEmail((String) resultData.get("email"));
                } else {
                    sysUserEntity.setEmail((String) resultData.get("mobile") + "@163.com");
                }
                sysUserEntity.setCreateTime(new Date());
                // sha256加密
                String salt = RandomStringUtils.randomAlphanumeric(20);
                sysUserEntity.setPassword(new Sha256Hash(sysUserEntity.getPassword(), salt).toHex());
                sysUserEntity.setSalt(salt);
                SysUserEntity UserEntity = sysUserService.select();
                long userId = 0;
                if (UserEntity == null || "".equals(UserEntity)) {
                    userId = 1;
                } else {
                    userId = UserEntity.getUserId() + 1;
                }
                sysUserEntity.setUserId(userId);
                sysUserService.addUser(sysUserEntity);
            }
        } catch (UnsupportedEncodingException e) {

        }

        return R.ok();
    }

    /**
     * 删除用戶信息
     *
     * @param
     * @return
     */
    @RequestMapping("/cas/DeleteUser")
    public void DeleteUser(@RequestBody String id) {
        try {
            String prevURL = "";
            String decodeURL = id;
            while (!prevURL.equals(decodeURL)) {
                prevURL = decodeURL;
                decodeURL = URLDecoder.decode(decodeURL, "UTF-8");
                String ids = decodeURL.split("=")[1];
                if (decodeURL.split("=")[1] != null) {

                    sysUserService.deleteUser(ids);
                }
            }
        } catch (UnsupportedEncodingException e) {

        }

    }

    /**
     * 修改用戶信息
     *
     * @param
     * @return
     */
    @RequestMapping("/cas/UpdateUser")
    public void UpdateUser(@RequestBody String param) {
        try {
            String prevURL = "";
            String decodeURL = param;
            while (!prevURL.equals(decodeURL)) {
                prevURL = decodeURL;
                decodeURL = URLDecoder.decode(decodeURL, "UTF-8");
                String reData = URLDecoder.decode(decodeURL, "UTF-8");
                log.info(reData);
                Map resultData = JsonUtil.toMap(reData.split("=")[1]);
                SysUserEntity sysUserEntity = new SysUserEntity();
                if ((Integer) resultData.get("id") != null) {
                    sysUserEntity.setDdid((Integer) resultData.get("id"));
                    sysUserEntity.setDeptId((String) resultData.get("deptId"));
                    sysUserEntity.setDeptNm((String) resultData.get("deptName"));
                    sysUserEntity.setUsername((String) resultData.get("userName"));
                    sysUserEntity.setMobile((String) resultData.get("mobile"));
                    sysUserEntity.setPassword((String) resultData.get("password"));
                    sysUserEntity.setIdCard((String) resultData.get("idCard"));
                    sysUserEntity.setDeptIdCard((String) resultData.get("deptIdCard"));
                    sysUserEntity.setEmail((String) resultData.get("email"));
                    sysUserEntity.setCreateTime(new Date());
                    // sha256加密
                    String salt = RandomStringUtils.randomAlphanumeric(20);
                    sysUserEntity.setPassword(new Sha256Hash(sysUserEntity.getPassword(), salt).toHex());
                    sysUserEntity.setSalt(salt);
                    sysUserService.updateUser(sysUserEntity);
                }

            }

        } catch (UnsupportedEncodingException e) {

        }
    }

}
