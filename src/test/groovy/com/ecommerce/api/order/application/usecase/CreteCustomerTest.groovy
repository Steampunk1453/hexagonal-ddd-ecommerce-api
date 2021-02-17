package com.ecommerce.api.order.application.usecase

import com.ecommerce.api.order.domain.OrderFixture
import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.model.customer.Address
import com.ecommerce.api.order.domain.model.customer.Customer
import com.ecommerce.api.order.domain.model.customer.PersonalData
import com.ecommerce.api.order.domain.port.CustomerRepository
import com.ecommerce.api.order.domain.port.OrderRepository

import spock.lang.Specification
import spock.lang.Subject

class CreteCustomerTest extends Specification {

    CustomerRepository customerRepository = Mock()

    OrderRepository orderRepository = Mock()

    @Subject
    CreateCustomer createCustomer = new CreateCustomer(customerRepository, orderRepository)

    def 'should create a new customer and return id'() {
        given:
            PersonalData personalData = new PersonalData("name", "surname")
            Address address = new Address("Rue Percebe", 13, 28008, "Madrid")
            Order order = OrderFixture.anyOrder()
            UUID orderId = order.id().value()
        when:
            UUID result = createCustomer.execute(personalData, address, orderId)
        then:
            1 * customerRepository.save(_ as Customer)
            1 * orderRepository.findById(_ as UUID) >> Optional.of(order)
            result != null
    }

}
