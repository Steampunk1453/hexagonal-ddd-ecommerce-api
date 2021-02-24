package com.ecommerce.api.order.adapter.web.response;

import java.time.LocalDate;

import com.ecommerce.api.order.domain.model.shipping.ShippingLabel;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ShippingResponse(@JsonProperty String trackingNumber, @JsonProperty LocalDate estimatedArrival) {

    public static ShippingResponse toResponse(final ShippingLabel shippingLabel) {
        return new ShippingResponse(
            shippingLabel.trackingNumber(),
            shippingLabel.estimatedArrival());
    }

}
