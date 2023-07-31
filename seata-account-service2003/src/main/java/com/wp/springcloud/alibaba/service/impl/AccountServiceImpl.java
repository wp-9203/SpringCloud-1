package com.wp.springcloud.alibaba.service.impl;

import com.wp.springcloud.alibaba.dao.AccountDao;
import com.wp.springcloud.alibaba.service.AccountSerivce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl implements AccountSerivce {

    @Resource
    private AccountDao accountDao;

    @Override
    public void descrease(Long userId, BigDecimal money) {
        log.info("-------扣减账户-------");
        accountDao.decrease(userId, money);
    }
}
