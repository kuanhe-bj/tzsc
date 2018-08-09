package io.renren.controller;

import io.renren.base.CountConsts;
import io.renren.common.utils.R;
import io.renren.config.RedisSequenceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangyulong
 * @date 2018/6/21
 */

@Slf4j
@RestController
@RequestMapping("/app")
public class InitCountController {


    @Autowired
    RedisSequenceFactory redisSequenceFactory;

    @RequestMapping("/set")
    public R setCountKey() {
        log.info("设置统计KEY到redis");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateStr = sf.format(date);
        redisSequenceFactory.set(CountConsts.HAVE_PLATE_COUNT + dateStr, 1, 2, TimeUnit.DAYS);
        redisSequenceFactory.set(CountConsts.NO_PLATE_COUNT + dateStr, 1, 2, TimeUnit.DAYS);
        redisSequenceFactory.set(CountConsts.ALL_COUNT + dateStr, 1, 2, TimeUnit.DAYS);
        return R.ok();
    }

}
