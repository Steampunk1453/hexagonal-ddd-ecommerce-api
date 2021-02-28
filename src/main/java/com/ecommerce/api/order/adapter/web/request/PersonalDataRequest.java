package com.ecommerce.api.order.adapter.web.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final record PersonalDataRequest(@JsonProperty String name, @JsonProperty String surname) {

    @JsonCreator
    public PersonalDataRequest(@JsonProperty String name, @JsonProperty String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "PersonalDataRequest[" +
                "name=" + name + ", " +
                "surname=" + surname + ']';
    }

}
