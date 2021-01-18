package com.ecommerce.api.order.adapter.web.response;

import com.ecommerce.api.order.domain.model.customer.Address;

public record GetAddressResponse(String street, Integer number, Integer cp, String town) {

    public static GetAddressResponse toResponse(final Address address) {
        return new GetAddressResponse(
            address.street(),
            address.number(),
            address.cp(),
            address.town());
    }

}
