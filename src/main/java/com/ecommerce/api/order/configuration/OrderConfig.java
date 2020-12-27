package com.ecommerce.api.order.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecommerce.api.order.application.usecase.CreateCustomer;
import com.ecommerce.api.order.application.usecase.CreateOrder;
import com.ecommerce.api.order.domain.port.CustomerRepository;
import com.ecommerce.api.order.domain.port.OrderRepository;

@Configuration
public class OrderConfig {

    @Bean
    CreateOrder createOrder(final OrderRepository orderRepository) {
        return new CreateOrder(orderRepository);
    }

    @Bean
    CreateCustomer createCustomer(final CustomerRepository customerRepository) {
        return new CreateCustomer(customerRepository);
    }

}
