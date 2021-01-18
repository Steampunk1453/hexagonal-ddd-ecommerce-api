package com.ecommerce.api.order.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecommerce.api.order.application.usecase.CreateCustomer;
import com.ecommerce.api.order.application.usecase.GetCustomer;
import com.ecommerce.api.order.domain.port.CustomerRepository;

@Configuration
public class CustomerConfig {

    @Bean
    CreateCustomer createCustomer(final CustomerRepository customerRepository) {
        return new CreateCustomer(customerRepository);
    }

    @Bean
    GetCustomer getCustomer(final CustomerRepository customerRepository) {
        return new GetCustomer(customerRepository);
    }

}
