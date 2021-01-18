package com.ecommerce.api.order.application.usecase

import com.ecommerce.api.order.domain.OrderProvider
import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.port.OrderRepository

import spock.lang.Specification
import spock.lang.Subject

class GetTotalPriceTest extends Specification {

    OrderRepository repository = Mock()

    @Subject
    GetTotalPrice getTotalPrice = new GetTotalPrice(repository)

    def 'should get total price of a order'() {
        given:
            Order order = Spy(OrderProvider.buildOrder())
            UUID id = UUID.randomUUID()
        when:
            getTotalPrice.execute(id)
        then:
            1 * repository.get(_ as UUID) >> order
    }

}
