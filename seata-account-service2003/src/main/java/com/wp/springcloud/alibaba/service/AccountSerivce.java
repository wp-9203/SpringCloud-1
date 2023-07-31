package com.wp.springcloud.alibaba.service;

import java.math.BigDecimal;

public interface AccountSerivce {

    void descrease(Long userId, BigDecimal money);
}
