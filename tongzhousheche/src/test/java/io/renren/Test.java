package io.renren;

import io.renren.vas.util.HttpUtil;

import java.util.Date;
import java.util.Map;

/**
 * @author zhangyulong
 * @date 2018/3/9
 */
public class Test {
    public static void main(String[] args) {

        Date date = new Date();
        HttpUtil httpUtil = new HttpUtil();

        String msg = "{  \"employees\": {  { \"firstName\":\"Bill\" , \"lastName\":\"Gates\" },  { \"firstName\":\"George\" , \"lastName\":\"Bush\" },  { \"firstName\":\"Thomas\" , \"lastName\":\"Carter\" }  }  }";


        for (int a = 0; a < 5000; a++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Map response = httpUtil.doPost("http://localhost:8081/vas/VIID/SubscribeNotifications", null, msg);
                        System.out.println(new Date().getTime() - date.getTime());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


    }


}
