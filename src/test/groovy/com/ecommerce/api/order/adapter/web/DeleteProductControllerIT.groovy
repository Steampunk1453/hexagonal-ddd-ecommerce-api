package com.ecommerce.api.order.adapter.web

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions

import com.ecommerce.api.order.domain.OrderFixture
import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.port.OrderRepository

import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

@Title("DeleteProductController Specification")
@Narrative("The Specification of the behaviour of the DeleteProductController. It can delete a product from an order previously created")
@SpringBootTest
@AutoConfigureMockMvc
class DeleteProductControllerIT extends Specification {

    @Autowired
    private MockMvc mvc

    @Autowired
    private OrderRepository orderRepository

    @DirtiesContext
    def "when delete is performed then the response has status 200"() {
        given:
            createOrder()
            Order order = getOrder()
            UUID orderId = order.id().value()
            UUID productId = order.orderItems().get(0).product().id()
        when:
            ResultActions result = mvc.perform(delete("/products/$orderId/$productId")
                    .contentType(MediaType.APPLICATION_JSON))
        then:
            result.andExpect(status().isOk())
    }

    private void createOrder() {
        orderRepository.save(OrderFixture.anyOrder())
    }

    private Order getOrder() {
        orderRepository.findAll().get(0)
    }

}

