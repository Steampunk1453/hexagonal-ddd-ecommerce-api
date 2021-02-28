package com.ecommerce.api.order.domain.model.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PersonalData(@JsonProperty String name, @JsonProperty String surname) {

}
