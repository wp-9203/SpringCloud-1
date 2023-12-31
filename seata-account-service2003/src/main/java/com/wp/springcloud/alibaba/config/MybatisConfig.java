package com.wp.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.wp.springcloud.alibaba.dao"})
public class MybatisConfig {
}
