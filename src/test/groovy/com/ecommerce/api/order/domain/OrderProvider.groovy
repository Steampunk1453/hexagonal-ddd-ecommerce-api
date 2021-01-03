package com.ecommerce.api.order.domain

import org.javamoney.moneta.Money

import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.model.Product

class OrderProvider {

    static Order buildOrder() {
        UUID orderId = UUID.randomUUID()
        UUID productId = UUID.randomUUID()
        Money value = Money.of(new BigDecimal(2.50), "EUR")
        Product product = new Product(productId, "PROD", "product", value)
        Integer productQuantity = 1
        new Order(orderId, product, productQuantity)
    }

}
