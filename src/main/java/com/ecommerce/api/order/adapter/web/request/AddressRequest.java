package com.ecommerce.api.order.adapter.web.request;

public record AddressRequest(String street, Integer number, Integer cp, String town) {

}
