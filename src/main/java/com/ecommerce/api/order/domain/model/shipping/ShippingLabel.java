package com.ecommerce.api.order.domain.model.shipping;

import java.time.LocalDate;

public record ShippingLabel(String trackingNumber, LocalDate estimatedArrival) {

}
