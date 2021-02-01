package com.ecommerce.api.order.application.usecase

import com.ecommerce.api.order.domain.OrderProvider
import com.ecommerce.api.order.domain.model.BusinessException
import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.port.OrderRepository

import spock.lang.Specification
import spock.lang.Subject

class DeleteProductTest extends Specification {

    OrderRepository repository = Mock()

    @Subject
    DeleteProduct deleteProduct = new DeleteProduct(repository)

    def 'should delete a product by id'() {
        given:
            Order order = Spy(OrderProvider.buildOrder())
            UUID orderId = order.id().value()
            UUID productId = order.orderItems().get(0).product().id()
        when:
            deleteProduct.execute(orderId, productId)
        then:
            1 * repository.findById(_ as UUID) >> Optional.of(order)
            1 * order.removeProduct(_ as UUID)
            1 * repository.save(_ as Order)
    }

    def 'should throw business exception when delete a product by id and OrderRepository returns null'() {
        given:
            Order order = OrderProvider.buildOrder()
            UUID orderId = order.id().value()
            UUID productId = order.orderItems().get(0).product().id()
        when:
            deleteProduct.execute(orderId, productId)
        then:
            1 * repository.findById(_ as UUID) >> Optional.ofNullable(null)
            thrown(BusinessException)
    }

}
