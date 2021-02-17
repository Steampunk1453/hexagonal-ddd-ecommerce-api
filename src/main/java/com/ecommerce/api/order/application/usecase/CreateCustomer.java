package com.ecommerce.api.order.application.usecase;

import java.util.UUID;

import com.ecommerce.api.order.domain.model.BusinessException;
import com.ecommerce.api.order.domain.model.customer.Address;
import com.ecommerce.api.order.domain.model.customer.Customer;
import com.ecommerce.api.order.domain.model.customer.PersonalData;
import com.ecommerce.api.order.domain.port.CustomerRepository;
import com.ecommerce.api.order.domain.port.OrderRepository;

public record CreateCustomer(CustomerRepository customerRepository, OrderRepository orderRepository) {

    public UUID execute(final PersonalData personalData, final Address address, final UUID orderId) {
        final var customer = new Customer(UUID.randomUUID(), personalData.name(),  personalData.surname(), address);
        customerRepository.save(customer);
        final var order = orderRepository.findById(orderId).orElseThrow(() -> new BusinessException("Order not found"));
        order.addCustomer(customer);
        return customer.id();
    }

}
