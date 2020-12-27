package com.ecommerce.api.order.domain.model;

import java.util.UUID;

public record Customer(UUID uuid, String name, String surname, Address address) {

}
