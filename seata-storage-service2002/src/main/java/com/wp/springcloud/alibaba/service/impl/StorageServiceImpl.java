package com.wp.springcloud.alibaba.service.impl;

import com.wp.springcloud.alibaba.dao.StorageDao;
import com.wp.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("----扣减库存-----");

        try{

            TimeUnit.SECONDS.sleep(20);
        } catch(Exception e){
            e.printStackTrace();
        }

        storageDao.decrease(productId,count);
    }
}
