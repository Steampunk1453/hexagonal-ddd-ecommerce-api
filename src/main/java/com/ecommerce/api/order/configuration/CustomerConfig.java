package com.ecommerce.api.order.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecommerce.api.order.application.usecase.CreateCustomer;
import com.ecommerce.api.order.application.usecase.DeleteCustomer;
import com.ecommerce.api.order.application.usecase.GetCustomer;
import com.ecommerce.api.order.domain.port.CustomerRepository;
import com.ecommerce.api.order.domain.port.OrderRepository;

@Configuration
public class CustomerConfig {

    @Bean
    CreateCustomer createCustomer(final CustomerRepository customerRepository,
                                  final OrderRepository orderRepository) {
        return new CreateCustomer(customerRepository, orderRepository);
    }

    @Bean
    GetCustomer getCustomer(final CustomerRepository customerRepository) {
        return new GetCustomer(customerRepository);
    }

    @Bean
    DeleteCustomer deleteCustomer(final CustomerRepository customerRepository,
                                  final OrderRepository orderRepository) {
        return new DeleteCustomer(customerRepository, orderRepository);
    }

}
