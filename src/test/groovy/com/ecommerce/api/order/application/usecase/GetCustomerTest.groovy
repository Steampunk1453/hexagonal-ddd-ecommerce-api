package com.ecommerce.api.order.application.usecase

import com.ecommerce.api.order.domain.port.CustomerRepository

import spock.lang.Specification
import spock.lang.Subject

class GetCustomerTest extends Specification {

    CustomerRepository repository = Mock()

    @Subject
    GetCustomer getCustomer = new GetCustomer(repository)

    def 'should get a customer by id'() {
        given:
            UUID id = UUID.randomUUID()
        when:
            getCustomer.execute(id)
        then:
            1 * repository.get(_ as UUID)
    }

}
