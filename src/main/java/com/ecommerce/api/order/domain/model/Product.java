package com.ecommerce.api.order.domain.model;

import java.util.UUID;

import org.javamoney.moneta.Money;

public record Product(UUID id, String description, Money value) {
}
