package com.ecommerce.api.order.application.usecase;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.ecommerce.api.order.domain.model.BusinessException;
import com.ecommerce.api.order.domain.model.Order;
import com.ecommerce.api.order.domain.model.shipping.ShippingLabel;
import com.ecommerce.api.order.domain.port.OrderRepository;
import com.ecommerce.api.order.domain.port.ShippingRepository;

public record ShipOrder(OrderRepository orderRepository, ShippingRepository shippingRepository) {

    public ShippingLabel execute(final UUID orderId) {
        final var order = orderRepository.findById(orderId).orElseThrow(() -> new BusinessException("Order not found"));
        if (isAvailableForUltraFastShipping(order)) {
            return shippingRepository.requestFastDelivery(order);
        }
        return shippingRepository.requestStandardDelivery(order);
    }

    private boolean isAvailableForUltraFastShipping(Order order) {
        String town = order.getCustomer().address().town();
        List<String> availableTowns = Arrays.asList("MAD", "BCN", "VLC");
        return availableTowns.contains(town);
    }

}
