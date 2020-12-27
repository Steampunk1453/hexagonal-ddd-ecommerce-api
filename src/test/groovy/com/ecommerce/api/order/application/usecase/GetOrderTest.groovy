package com.ecommerce.api.order.application.usecase

import com.ecommerce.api.order.domain.port.OrderRepository

import spock.lang.Specification
import spock.lang.Subject

class GetOrderTest extends Specification {

    OrderRepository repository = Mock()

    @Subject
    GetOrder getOrder = new GetOrder(repository)

    def 'should get an order by id'() {
        given:
            UUID id = UUID.randomUUID()
        when:
            getOrder.execute(id)
        then:
            1 * repository.get(_ as UUID)
    }

}
