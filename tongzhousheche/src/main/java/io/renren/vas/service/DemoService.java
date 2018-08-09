package io.renren.vas.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service层示例
 * @author zhangyulong
 * @date 2018/2/8
 */
@Slf4j
@Service
public class DemoService {

    public void demo(){
        log.info("Service 日志示例");
    }
}
