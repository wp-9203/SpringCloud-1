package com.wp.springcloud.alibaba.service.impl;

import com.wp.springcloud.alibaba.dao.OrderDao;
import com.wp.springcloud.alibaba.entities.Order;
import com.wp.springcloud.alibaba.service.AccountService;
import com.wp.springcloud.alibaba.service.OrderService;
import com.wp.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;


    @Override
    @GlobalTransactional(name = "tx_order_group",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("-------开始创建订单--------");
        orderDao.create(order);
        log.info("--------订单微服务调用库存，做扣减操作---start---");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("--------订单微服务调用库存，做扣减操作---end----");
        log.info("--------订单微服务调用账户，做扣减操作---start---");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("--------订单微服务调用账户，做扣减操作---start---");

        log.info("-------修改订单状态---start---");
        orderDao.update(order.getUserId(), order.getStatus());
        log.info("-------修改订单状态---end---");

    }
}
