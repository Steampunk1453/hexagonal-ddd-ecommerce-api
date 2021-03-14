package com.ecommerce.api.order.adapter.web.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final record AddressRequest(@JsonProperty String street, @JsonProperty Integer number, @JsonProperty Integer cp,
                                   @JsonProperty String town) {

    @JsonCreator
    public AddressRequest(@JsonProperty String street, @JsonProperty Integer number, @JsonProperty Integer cp, @JsonProperty String town) {
        this.street = street;
        this.number = number;
        this.cp = cp;
        this.town = town;
    }

    @Override
    public String toString() {
        return "AddressRequest[" +
            "street=" + street + ", " +
            "number=" + number + ", " +
            "cp=" + cp + ", " +
            "town=" + town + ']';
    }

}
