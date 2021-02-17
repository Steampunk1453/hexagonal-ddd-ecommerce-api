package com.ecommerce.api.order.application.usecase

import com.ecommerce.api.order.domain.CustomerFixture
import com.ecommerce.api.order.domain.OrderFixture
import com.ecommerce.api.order.domain.model.BusinessException
import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.model.customer.Customer
import com.ecommerce.api.order.domain.port.CustomerRepository
import com.ecommerce.api.order.domain.port.OrderRepository

import spock.lang.Specification
import spock.lang.Subject

class DeleteCustomerTest extends Specification {

    CustomerRepository customerRepository = Mock()

    OrderRepository orderRepository = Mock()

    @Subject
    DeleteCustomer deleteCustomer = new DeleteCustomer(customerRepository, orderRepository)

    def 'should delete a product by id'() {
        given:
            Customer customer = CustomerFixture.anyCustomer()
            UUID customerId = customer.id()
            Order order = Spy(OrderFixture.anyOrder())
            UUID orderId = order.id().value()
        when:
            deleteCustomer.execute(customerId, orderId)
        then:
            1 * customerRepository.delete(_ as UUID)
            1 * orderRepository.findById(_ as UUID) >> Optional.of(order)
            1 * order.removeCustomer()
    }

    def 'should throw business exception when delete a product by id and OrderRepository returns null'() {
        given:
            Customer customer = CustomerFixture.anyCustomer()
            UUID customerId = customer.id()
            Order order = OrderFixture.anyOrder()
            UUID orderId = order.id().value()
        when:
            deleteCustomer.execute(customerId, orderId)
        then:
            1 * customerRepository.delete(_ as UUID)
            1 * orderRepository.findById(_ as UUID) >> Optional.ofNullable(null)
            thrown(BusinessException)
    }

}
