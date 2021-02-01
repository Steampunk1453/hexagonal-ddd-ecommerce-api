package com.ecommerce.api.order.application.usecase

import com.ecommerce.api.order.domain.OrderProvider
import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.port.OrderRepository

import spock.lang.Specification
import spock.lang.Subject

class GetAllOrdersTest extends Specification {

    OrderRepository repository = Mock()

    @Subject
    GetAllOrders getAllOrders = new GetAllOrders(repository)

    def 'should get all orders'() {
        given:
            Order order = OrderProvider.buildOrder()
            List<Order> orders = new ArrayList<>()
            orders.add(order)
        when:
            List<Order> result = getAllOrders.execute()
        then:
            1 * repository.findAll() >> orders
            result.size() > 0
            result.get(0).id().value() == order.id().value()
    }

}
