package com.ecommerce.api.order.adapter.web.response;

import com.ecommerce.api.order.domain.model.customer.Address;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressResponse(@JsonProperty String street, @JsonProperty Integer number, @JsonProperty Integer cp, @JsonProperty String town) {

    public static AddressResponse toResponse(final Address address) {
        return new AddressResponse(
            address.street(),
            address.number(),
            address.cp(),
            address.town());
    }

}
