package com.ecommerce.api.order.adapter.web.request;

import java.util.Date;

public record PayOrderRequest(String cardName, String cardNumber, Date expirationDate, String verificationCode) {

}
