package com.ecommerce.api.order.domain;

class DomainException extends RuntimeException {
    DomainException(final String message) {
        super(message);
    }
}
