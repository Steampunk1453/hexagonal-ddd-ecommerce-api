package com.ecommerce.api.order.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecommerce.api.order.application.usecase.CreateCustomer;
import com.ecommerce.api.order.application.usecase.CreateOrder;
import com.ecommerce.api.order.domain.model.PriceCalculatorService;
import com.ecommerce.api.order.domain.port.CustomerRepository;
import com.ecommerce.api.order.domain.port.OrderRepository;
import com.ecommerce.api.order.domain.port.ProductRepository;

@Configuration
public class OrderConfig {

    @Bean
    CreateOrder createOrder(final OrderRepository orderRepository,
                            final ProductRepository productRepository,
                            final PriceCalculatorService priceCalculatorService) {
        return new CreateOrder(orderRepository, productRepository, priceCalculatorService);
    }

    @Bean
    CreateCustomer createCustomer(final CustomerRepository customerRepository) {
        return new CreateCustomer(customerRepository);
    }

}
