package com.ecommerce.api.order.domain

import com.ecommerce.api.order.domain.model.BusinessException
import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.model.OrderId
import com.ecommerce.api.order.domain.model.Product
import com.ecommerce.api.order.domain.model.customer.Customer

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
            Order result = new Order(OrderId.of(id), product, productQuantity, itemPrice)
        then:
            result.id().value() == id
            result.orderItems().size() == 1
            result.orderItems()[0].product() == product
            result.totalPrice() == 10.00
    }

    def 'should add a product to an order'() {
        given:
            Order order = OrderFixture.anyOrder()
            BigDecimal productPrice = new BigDecimal(12.50)
            Product newProduct = new Product(UUID.randomUUID(), "BOOK", "newProduct", productPrice)
            Integer productQuantity = 2
            BigDecimal itemPrice = new BigDecimal(25.00)
        when:
            order.addProduct(newProduct, productQuantity, itemPrice)
            Order result = order
        then:
            result.id().value() == order.id().value()
            result.orderItems().size() == 2
            result.orderItems()[1].product() == newProduct
            result.totalPrice() == 27.50
    }

    def 'should update a previous product with new quantity'() {
        given:
            Order order = OrderFixture.anyOrder()
            UUID productId = order.orderItems().get(0).product().id()
            BigDecimal productPrice = new BigDecimal(2.50)
            Product oldProduct = new Product(productId, "STICKER", "oldProduct", productPrice)
            Integer productQuantity = 2
            BigDecimal itemPrice = new BigDecimal(5.00)
        when:
            order.addProduct(oldProduct, productQuantity, itemPrice)
            Order result = order
        then:
            result.id().value() == order.id().value()
            result.orderItems().size() == 1
            result.orderItems()[0].product().id() == oldProduct.id()
            result.orderItems()[0].quantity() == 3
            result.orderItems()[0].price() == 7.50
            result.totalPrice() == 7.50
    }

    def 'should remove a product from an order'() {
        given:
            Order order = OrderFixture.anyOrder()
            UUID productId = order.orderItems().get(0).product().id()
        when:
            order.removeProduct(productId)
            Order result = order
        then:
            result.orderItems().size() == 0
            result.totalPrice() == BigDecimal.ZERO
    }

    def 'should add a customer to an order'() {
        given:
            Customer customer = CustomerFixture.anyCustomer()
            Order order = OrderFixture.anyOrder()
        when:
            order.addCustomer(customer)
            Customer result = order.getCustomer()
        then:
            result.id() == customer.id()
            result.name() == customer.name()
            result.surname() == customer.surname()
            result.address() == customer.address()
            result.address().street() == customer.address().street()
            result.address().number() == customer.address().number()
            result.address().cp() == customer.address().cp()
            result.address().town() == customer.address().town()
    }

    def 'should remove a customer from an order'() {
        given:
            Order order = OrderFixture.anyOrder()
        when:
            order.removeCustomer()
            Order result = order
        then:
            result.getCustomer() == null
    }

    def 'should throw business exception when add a null product to an order'() {
        given:
            Order order = OrderFixture.anyOrder()
            Integer productQuantity = 2
            BigDecimal itemPrice = new BigDecimal(2.50)
        when:
            order.addProduct(null, productQuantity, itemPrice)
        then:
            thrown(BusinessException)
    }

}
