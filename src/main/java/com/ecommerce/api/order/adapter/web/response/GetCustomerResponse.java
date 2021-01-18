package com.ecommerce.api.order.adapter.web.response;

import java.util.UUID;

import com.ecommerce.api.order.domain.model.customer.Customer;

public record GetCustomerResponse(UUID id, String name, String surname, GetAddressResponse address) {

    public static GetCustomerResponse toResponse(final Customer customer) {
        return new GetCustomerResponse(
            customer.id(),
            customer.name(),
            customer.surname(),
            GetAddressResponse.toResponse(customer.address()));
    }

}
