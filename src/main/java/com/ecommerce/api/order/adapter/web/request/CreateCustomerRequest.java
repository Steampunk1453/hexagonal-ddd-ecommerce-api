package com.ecommerce.api.order.adapter.web.request;

public record CreateCustomerRequest(String name, String surname, AddressRequest address) {

}
