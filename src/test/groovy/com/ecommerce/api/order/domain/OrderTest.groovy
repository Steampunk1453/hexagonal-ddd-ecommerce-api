package com.ecommerce.api.order.domain

import com.ecommerce.api.order.domain.model.DomainException
import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.model.Product

import spock.lang.Specification

class OrderTest extends Specification {

    def 'should create an order with a product'() {
        given:
            UUID id = UUID.randomUUID()
            Integer productQuantity = 4
            BigDecimal productPrice = new BigDecimal(2.50)
            Product product = new Product(id, "STICKER", "product", productPrice)
            BigDecimal itemPrice = new BigDecimal(10.00)
        when:
            Order result = new Order(id, product, productQuantity, itemPrice)
        then:
            result.id == id
            result.orderItems.size() == 1
            result.orderItems[0].product() == product
            result.totalPrice == 10.00
    }

    def 'should add a product to an order'() {
        given:
            Order order = OrderProvider.buildOrder()
            BigDecimal productPrice = new BigDecimal(12.50)
            Product newProduct = new Product(UUID.randomUUID(), "BOOK", "newProduct", productPrice)
            Integer productQuantity = 2
            BigDecimal itemPrice = new BigDecimal(25.00)
        when:
            order.addProduct(newProduct, productQuantity, itemPrice)
            Order result = order
        then:
            result.id == order.id
            result.orderItems[1].product() == newProduct
            result.orderItems.size() == 2
            result.totalPrice == 27.50
    }

    def 'should remove a product from an order'() {
        given:
            Order order = OrderProvider.buildOrder()
            UUID productId = order.orderItems.get(0).product().id()
        when:
            order.removeProduct(productId)
            Order result = order
        then:
            result.orderItems.size() == 0
            result.totalPrice == BigDecimal.ZERO
    }

    def 'should throw domain exception when add a null product to an order'() {
        given:
            Order order = OrderProvider.buildOrder()
            Integer productQuantity = 2
            BigDecimal itemPrice = new BigDecimal(2.50)
        when:
            order.addProduct(null, productQuantity, itemPrice)
        then:
            thrown(DomainException)
    }

}
