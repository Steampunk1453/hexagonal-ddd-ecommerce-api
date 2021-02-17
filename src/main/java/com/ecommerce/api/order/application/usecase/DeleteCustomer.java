package com.ecommerce.api.order.application.usecase;

import java.util.UUID;

import com.ecommerce.api.order.domain.model.BusinessException;
import com.ecommerce.api.order.domain.port.CustomerRepository;
import com.ecommerce.api.order.domain.port.OrderRepository;

public record DeleteCustomer(CustomerRepository customerRepository, OrderRepository orderRepository) {

    public void execute(final UUID customerId, final UUID orderId) {
        customerRepository.delete(customerId);
        final var order = orderRepository.findById(orderId).orElseThrow(() -> new BusinessException("Order not found"));
        order.removeCustomer();
    }

}
