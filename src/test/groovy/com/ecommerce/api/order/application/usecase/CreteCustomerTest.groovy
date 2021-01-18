package com.ecommerce.api.order.application.usecase

import com.ecommerce.api.order.domain.model.customer.Address
import com.ecommerce.api.order.domain.model.customer.Customer
import com.ecommerce.api.order.domain.port.CustomerRepository

import spock.lang.Specification
import spock.lang.Subject

class CreteCustomerTest extends Specification {

    CustomerRepository repository = Mock()

    @Subject
    CreateCustomer createCustomer = new CreateCustomer(repository)

    def 'should create a new customer and return id'() {
        given:
            Address address = new Address("Rue Percebe", 13, 28008, "Madrid")
        when:
            UUID result = createCustomer.execute("name", "surname", address)
        then:
            1 * repository.save(_ as Customer)
            result != null
    }

}
