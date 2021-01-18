package com.ecommerce.api.order.domain.model.customer;

import com.ecommerce.api.order.adapter.web.request.AddressRequest;

public record Address(String street, Integer number, Integer cp, String town) {

    public static Address toDomain(final AddressRequest request) {
        return new Address(
            request.street(),
            request.number(),
            request.cp(),
            request.town());
    }

}
