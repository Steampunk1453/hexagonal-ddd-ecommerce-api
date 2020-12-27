package com.ecommerce.api.order.application.usecase

import com.ecommerce.api.order.domain.port.OrderRepository

import spock.lang.Specification
import spock.lang.Subject

class DeleteProductTest extends Specification {

    OrderRepository repository = Mock()

    @Subject
    DeleteProduct deleteProduct = new DeleteProduct(repository)

    def 'should delete an order by id'() {
        given:
            UUID id = UUID.randomUUID()
        when:
            deleteProduct.execute(id)
        then:
            1 * repository.delete(_ as UUID)
    }

}
