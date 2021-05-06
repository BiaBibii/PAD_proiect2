package com.cantina.service;

import com.cantina.model.Payment;
import com.cantina.model.UserPayment;

public interface PaymentService {
	Payment setByUserPayment(UserPayment userPayment,Payment payment);
}
