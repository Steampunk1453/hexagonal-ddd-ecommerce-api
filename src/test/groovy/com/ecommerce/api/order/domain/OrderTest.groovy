package com.ecommerce.api.order.domain

import org.javamoney.moneta.Money

import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.model.Product

import spock.lang.Specification

class OrderTest extends Specification {

    def 'should add a product to an order'() {
        given:
            UUID id = UUID.randomUUID()
            Order order = createOrder(id)
            Money newValue = Money.of(new BigDecimal(2.5), "EUR")
            Product newProduct = new Product(UUID.randomUUID(), "newProduct", newValue)
        when:
            order.addProduct(newProduct)
            int sizeResult = order.getOrderItems().size()
            int priceResult = order.getPrice()
        then:
            sizeResult == 2
            priceResult == 5
    }

    def 'should remove an order'() {
        given:
            UUID id = UUID.randomUUID()
            Order order = createOrder(id)
        when:
            order.remove(id)
            int sizeResult = order.getOrderItems().size()
            BigDecimal priceResult = order.getPrice()
        then:
            sizeResult == 0
            priceResult == BigDecimal.ZERO
    }

    private static Order createOrder(UUID id) {
        Money value = Money.of(new BigDecimal(2.5), "EUR")
        Product product = new Product(id, "product", value)
        new Order(id, product)
    }

}
