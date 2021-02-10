package com.ecommerce.api.order.adapter.web.response;

import java.util.UUID;

import com.ecommerce.api.order.domain.model.customer.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;

public record GetCustomerResponse(@JsonProperty UUID id, @JsonProperty String name, @JsonProperty String surname, @JsonProperty GetAddressResponse address) {

    public static GetCustomerResponse toResponse(final Customer customer) {
        return new GetCustomerResponse(
            customer.id(),
            customer.name(),
            customer.surname(),
            GetAddressResponse.toResponse(customer.address()));
    }

}
