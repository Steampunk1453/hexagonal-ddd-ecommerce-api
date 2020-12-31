package com.ecommerce.api.order.domain.model;

public class DomainException extends RuntimeException {
    DomainException(final String message) {
        super(message);
    }
}
