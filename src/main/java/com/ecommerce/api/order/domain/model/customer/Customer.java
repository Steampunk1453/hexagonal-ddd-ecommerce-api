package com.ecommerce.api.order.domain.model.customer;

import java.util.UUID;

public record Customer(UUID id, String name, String surname, Address address) {

}
