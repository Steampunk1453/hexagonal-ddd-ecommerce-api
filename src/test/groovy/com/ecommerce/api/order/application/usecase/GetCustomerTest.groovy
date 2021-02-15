package com.ecommerce.api.order.application.usecase

import com.ecommerce.api.order.domain.CustomerFixture
import com.ecommerce.api.order.domain.model.BusinessException
import com.ecommerce.api.order.domain.model.customer.Customer
import com.ecommerce.api.order.domain.port.CustomerRepository

import spock.lang.Specification
import spock.lang.Subject

class GetCustomerTest extends Specification {

    CustomerRepository repository = Mock()

    @Subject
    GetCustomer getCustomer = new GetCustomer(repository)

    def 'should get a customer by id'() {
        given:
        Customer customer = CustomerFixture.anyCustomer()
        UUID customerId = customer.id()
        when:
        getCustomer.execute(customerId)
        then:
        1 * repository.findById(_ as UUID) >> Optional.of(customer)
    }

    def 'should throw business exception when get a customer by id'() {
        given:
            UUID id = UUID.randomUUID()
        when:
            getCustomer.execute(id)
        then:
            1 * repository.findById(_ as UUID) >> Optional.ofNullable(null)
            thrown(BusinessException)
    }

}
