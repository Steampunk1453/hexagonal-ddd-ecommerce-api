package com.ecommerce.api.order.domain.model.customer;

import com.ecommerce.api.order.adapter.web.request.PersonalDataRequest;

public record PersonalData(String name, String surname) {

    public static PersonalData toDomain(final PersonalDataRequest request) {
        return new PersonalData(
                request.name(),
                request.surname());
    }

}
