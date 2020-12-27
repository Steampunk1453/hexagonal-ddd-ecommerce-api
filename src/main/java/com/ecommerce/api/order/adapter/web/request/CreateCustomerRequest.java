package com.ecommerce.api.order.adapter.web.request;

import com.ecommerce.api.order.domain.model.Address;

public record CreateCustomerRequest(String name, String surname, Address address) {

}
