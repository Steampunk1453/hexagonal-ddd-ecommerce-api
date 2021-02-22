package com.ecommerce.api.order.adapter.web.request;

import java.util.Date;

public record PayOrderRequest(String cardholder, String cardNumber, Date expirationDate, String verificationCode) {

}
