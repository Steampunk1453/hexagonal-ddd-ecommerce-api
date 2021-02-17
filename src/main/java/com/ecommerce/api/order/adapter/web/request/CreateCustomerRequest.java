package com.ecommerce.api.order.adapter.web.request;

import java.util.UUID;

import com.ecommerce.api.order.domain.model.customer.PersonalData;

public record CreateCustomerRequest(PersonalData personalData, AddressRequest address, UUID orderId) {

}
