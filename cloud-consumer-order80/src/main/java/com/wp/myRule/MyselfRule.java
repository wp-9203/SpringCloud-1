package com.wp.myRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ribbon的自定义配置类不能放在@ComponentScan所扫描的当前包及其子包
 * 否则我们自定义的这个配置类就会被所有的Ribbon客户端共享，达不到特殊定制的目的
 */
@Configuration
public class MyselfRule {
    @Bean
    public IRule myRule(){
        return  new RandomRule();
    }
}
