package com.ecommerce.api.order.adapter.web.response;

import com.ecommerce.api.order.domain.model.customer.Address;
import com.fasterxml.jackson.annotation.JsonProperty;

public record GetAddressResponse(@JsonProperty String street, @JsonProperty Integer number, @JsonProperty Integer cp, @JsonProperty String town) {

    public static GetAddressResponse toResponse(final Address address) {
        return new GetAddressResponse(
            address.street(),
            address.number(),
            address.cp(),
            address.town());
    }

}
