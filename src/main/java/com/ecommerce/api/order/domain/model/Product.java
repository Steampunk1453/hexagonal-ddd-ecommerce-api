package com.ecommerce.api.order.domain.model;

import java.util.UUID;

import org.javamoney.moneta.Money;

public record Product(UUID id, String code, String description, Money value) {
}
