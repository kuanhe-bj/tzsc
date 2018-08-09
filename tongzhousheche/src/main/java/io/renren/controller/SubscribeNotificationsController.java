package io.renren.controller;

import com.tceasy.common.utils.date.DateUtils;
import io.renren.base.CountConsts;
import io.renren.service.ETCPService;
import io.renren.service.SubscribeNotificationsService;
import io.renren.vas.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 订阅一所的回流数据后接收通知
 *
 * @author zhangyulong
 * @date 2018/3/7
 */
@Slf4j
@RestController
public class SubscribeNotificationsController {

    @Autowired
    private ETCPService scETCPService;

    @Autowired
    SubscribeNotificationsService subscribeNotificationsService;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 接收一所数据接口
     *
     * @param param
     * @return
     */
    @RequestMapping("/VIID/SubscribeNotifications")
    public String list(@RequestBody String param) {
        log.info(param);
        scETCPService.count(CountConsts.ALL_COUNT);
        subscribeNotificationsService.processor(param);
//        String sid = scETCPDao.findSID("subscribeID");
        return getReturnMsg();
    }

    /**
     * 生成响应一所接口的返回数据
     *
     * @return
     */
    private String getReturnMsg() {
        String sid = (String) redisTemplate.opsForValue().get(CountConsts.SUBSCRIBE_ID);//去redis中取数据
        return "{\n" +
                "    \"ResponseStatusListObject\": {\n" +
                "        \"ResponseStatusObject\": [\n" +
                "            {\n" +
                "                \"Id\": \"" + sid + "\", \n" +
                "                \"StatusCode\": \"0\",\n" +
                "                \"RequestURL\": \"/VIID/SubscribeNotifications\",\n" +
                "                \"StatusString\": \"正常\",\n" +
                "                \"LocalTime\": \"" + DateUtils.currentYyyyMMddHHmmss() + "\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
    }

    /**
     * 获取用戶信息
     *
     * @param
     * @return
     */
    @RequestMapping("/VIID/users")
    public void getUsers() {
        String requestPath = "http://1.180.90.146:11675/rkwmregister/getUsers";
        HttpUtil httpUtil = new HttpUtil();
        log.info("进度：{}", "这里");
        String msg = "";
        try {
            Map response = httpUtil.doPost(requestPath, null, msg);
            log.info("返回结果：{}", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
