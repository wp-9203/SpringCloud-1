package com.wp.springcloud.service;

import com.wp.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    public int save(Payment payment);

    public Payment getPaymentById(@Param("id") long id);
}
