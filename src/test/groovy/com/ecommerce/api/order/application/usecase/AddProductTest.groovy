package com.ecommerce.api.order.application.usecase

import org.javamoney.moneta.Money

import com.ecommerce.api.order.domain.Order
import com.ecommerce.api.order.domain.product.Product
import com.ecommerce.api.order.domain.port.OrderRepository

import spock.lang.Specification
import spock.lang.Subject

class AddProductTest extends Specification {

    OrderRepository repository = Mock()

    @Subject
    AddProduct addProduct = new AddProduct(repository)

    def 'should get an order and update it with a product'() {
        given:
            UUID id = UUID.randomUUID()
            Money value = Money.of(new BigDecimal(2.5), "EUR")
            Product product = new Product(id, "product", value)
        when:
            addProduct.execute(id, product)
        then:
            1 * repository.get(_ as UUID) >> new Order(id, product)
            1 * repository.update(_ as Order)
    }

}
