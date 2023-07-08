package com.wp.springcloud.dao;

import com.wp.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    public int save(Payment payment);

    public Payment getPaymentById(@Param("id") long id);
}
