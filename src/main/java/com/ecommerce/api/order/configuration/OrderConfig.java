package com.ecommerce.api.order.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecommerce.api.order.application.usecase.AddProduct;
import com.ecommerce.api.order.application.usecase.CreateOrder;
import com.ecommerce.api.order.application.usecase.DeleteOrder;
import com.ecommerce.api.order.application.usecase.DeleteProduct;
import com.ecommerce.api.order.application.usecase.GetAllOrders;
import com.ecommerce.api.order.application.usecase.GetOrder;
import com.ecommerce.api.order.application.usecase.GetTotalPrice;
import com.ecommerce.api.order.application.usecase.PayOrder;
import com.ecommerce.api.order.application.usecase.ShipOrder;
import com.ecommerce.api.order.domain.model.discount.PriceCalculatorService;
import com.ecommerce.api.order.domain.port.OrderRepository;
import com.ecommerce.api.order.domain.port.PaymentRepository;
import com.ecommerce.api.order.domain.port.ProductRepository;
import com.ecommerce.api.order.domain.port.ShippingRepository;

@Configuration
public class OrderConfig {

    @Bean
    CreateOrder createOrder(final OrderRepository orderRepository,
                            final ProductRepository productRepository,
                            final PriceCalculatorService priceCalculatorService) {
        return new CreateOrder(orderRepository, productRepository, priceCalculatorService);
    }

    @Bean
    GetOrder getOrder(final OrderRepository orderRepository) {
        return new GetOrder(orderRepository);
    }

    @Bean
    GetAllOrders getAllOrders(final OrderRepository orderRepository) {
        return new GetAllOrders(orderRepository);
    }

    @Bean
    DeleteOrder deleteOrder(final OrderRepository orderRepository) {
        return new DeleteOrder(orderRepository);
    }

    @Bean
    PayOrder payOrder(final OrderRepository orderRepository,
                      final PaymentRepository paymentRepository) {
        return new PayOrder(orderRepository, paymentRepository);
    }

    @Bean
    AddProduct addProduct(final OrderRepository orderRepository,
                          final ProductRepository productRepository,
                          final PriceCalculatorService priceCalculatorService) {
        return new AddProduct(orderRepository, productRepository, priceCalculatorService);
    }

    @Bean
    DeleteProduct deleteProduct(final OrderRepository orderRepository) {
        return new DeleteProduct(orderRepository);
    }

    @Bean
    GetTotalPrice getTotalPrice(final OrderRepository orderRepository) {
        return new GetTotalPrice(orderRepository);
    }

    @Bean
    ShipOrder shipOrder(final OrderRepository orderRepository,
                        final ShippingRepository shippingRepository) {
        return new ShipOrder(orderRepository, shippingRepository);
    }


}
