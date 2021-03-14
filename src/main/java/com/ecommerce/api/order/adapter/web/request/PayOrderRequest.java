package com.ecommerce.api.order.adapter.web.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final record PayOrderRequest(@JsonProperty String cardholder, @JsonProperty String cardNumber, @JsonProperty Date expirationDate,
                                    @JsonProperty String verificationCode) {

    @JsonCreator
    public PayOrderRequest(@JsonProperty String cardholder, @JsonProperty String cardNumber, @JsonProperty Date expirationDate, @JsonProperty String verificationCode) {
        this.cardholder = cardholder;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.verificationCode = verificationCode;
    }

    @Override
    public String toString() {
        return "PayOrderRequest[" +
            "cardholder=" + cardholder + ", " +
            "cardNumber=" + cardNumber + ", " +
            "expirationDate=" + expirationDate + ", " +
            "verificationCode=" + verificationCode + ']';
    }

}
