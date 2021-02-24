package com.ecommerce.api.order.domain

import com.ecommerce.api.order.domain.model.customer.Address
import com.ecommerce.api.order.domain.model.customer.Customer

class CustomerFixture {

    static Customer anyCustomer() {
        UUID id = UUID.randomUUID()
        Address address = new Address("Rue Percebe", 13, 28008, "MAD")
        new Customer(id, "name", "surname", address)
    }

}
