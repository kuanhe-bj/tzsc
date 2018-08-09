package io.renren.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhangyulong
 * @date 2018/3/9
 */
@Component
public class ProjectBaseData {

    public final String PRODUCE = "pro";

    @Value("${spring.profiles.active}")
    String operatingEnvironment;

    public String getPRODUCE() {
        return PRODUCE;
    }

    public String getOperatingEnvironment() {
        return operatingEnvironment;
    }

    public void setOperatingEnvironment(String operatingEnvironment) {
        this.operatingEnvironment = operatingEnvironment;
    }
}
