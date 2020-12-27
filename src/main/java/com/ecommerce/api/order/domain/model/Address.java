package com.ecommerce.api.order.domain.model;

import java.util.UUID;

public record Address(UUID id, String street, Integer number, Integer cp, String town) {

}
