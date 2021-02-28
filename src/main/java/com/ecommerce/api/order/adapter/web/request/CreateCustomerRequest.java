package com.ecommerce.api.order.adapter.web.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public final record CreateCustomerRequest(@JsonProperty PersonalDataRequest personalData,
                                          @JsonProperty AddressRequest address, @JsonProperty UUID orderId) {

    @JsonCreator
    public CreateCustomerRequest(@JsonProperty PersonalDataRequest personalData, @JsonProperty AddressRequest address, @JsonProperty UUID orderId) {
        this.personalData = personalData;
        this.address = address;
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "CreateCustomerRequest[" +
            "personalData=" + personalData + ", " +
            "address=" + address + ", " +
            "orderId=" + orderId + ']';
    }

}
