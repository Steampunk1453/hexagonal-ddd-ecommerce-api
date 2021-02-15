package com.ecommerce.api.order.application.usecase

import com.ecommerce.api.order.domain.OrderFixture
import com.ecommerce.api.order.domain.model.BusinessException
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
            Order order = Spy(OrderFixture.anyOrder())
            UUID id = UUID.randomUUID()
        when:
            getTotalPrice.execute(id)
        then:
            1 * repository.findById(_ as UUID) >> Optional.of(order)
    }

    def 'should throw business exception when get total price and OrderRepository returns null'() {
        given:
            UUID id = UUID.randomUUID()
        when:
            getTotalPrice.execute(id)
        then:
            1 * repository.findById(_ as UUID) >> Optional.ofNullable(null)
            thrown(BusinessException)
    }

}
