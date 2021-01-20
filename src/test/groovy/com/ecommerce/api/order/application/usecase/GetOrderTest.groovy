package com.ecommerce.api.order.application.usecase

import com.ecommerce.api.order.domain.OrderProvider
import com.ecommerce.api.order.domain.model.BusinessException
import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.port.OrderRepository

import spock.lang.Specification
import spock.lang.Subject

class GetOrderTest extends Specification {

    OrderRepository repository = Mock()

    @Subject
    GetOrder getOrder = new GetOrder(repository)

    def 'should get an find by id'() {
        given:
        Order order = OrderProvider.buildOrder()
        UUID id = UUID.randomUUID()
        when:
        getOrder.execute(id)
        then:
        1 * repository.findById(_ as UUID) >> Optional.of(order)
    }

    def 'should throw business exception when find an order by id and OrderRepository returns null'() {
        given:
        UUID id = UUID.randomUUID()
        when:
        getOrder.execute(id)
        then:
        1 * repository.findById(_ as UUID) >> Optional.ofNullable(null)
        thrown(BusinessException)
    }

}
