package com.ecommerce.api.order.domain.model;

public class BusinessException extends RuntimeException {

    public BusinessException(final String message) {
        super(message);
    }
}
