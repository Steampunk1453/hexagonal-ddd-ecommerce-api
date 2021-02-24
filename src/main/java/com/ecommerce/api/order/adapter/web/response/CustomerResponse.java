package com.ecommerce.api.order.adapter.web.response;

import java.util.UUID;

import com.ecommerce.api.order.domain.model.customer.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomerResponse(@JsonProperty UUID id, @JsonProperty String name, @JsonProperty String surname, @JsonProperty AddressResponse address) {

    public static CustomerResponse toResponse(final Customer customer) {
        return new CustomerResponse(
            customer.id(),
            customer.name(),
            customer.surname(),
            AddressResponse.toResponse(customer.address()));
    }

}
