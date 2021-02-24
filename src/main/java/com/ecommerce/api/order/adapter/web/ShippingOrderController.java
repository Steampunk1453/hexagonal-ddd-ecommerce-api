package com.ecommerce.api.order.adapter.web;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.order.adapter.web.response.ShippingResponse;
import com.ecommerce.api.order.application.usecase.ShipOrder;

@RestController
public class ShippingOrderController {

    private final ShipOrder shipOrder;

    public ShippingOrderController(ShipOrder shipOrder) {
        this.shipOrder = shipOrder;
    }

    @PostMapping("shippings/{orderId}")
    ResponseEntity<ShippingResponse> ship(@PathVariable("orderId") final UUID orderId) {
        final var shippingLabel = shipOrder.execute(orderId);
        return new ResponseEntity<>(ShippingResponse.toResponse(shippingLabel), HttpStatus.CREATED);
    }

}
