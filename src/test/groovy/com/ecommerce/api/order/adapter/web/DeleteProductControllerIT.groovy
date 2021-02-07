package com.ecommerce.api.order.adapter.web

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions

import com.ecommerce.api.order.domain.OrderFixture
import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.port.OrderRepository
import com.ecommerce.api.order.domain.port.ProductRepository
import com.fasterxml.jackson.databind.ObjectMapper

import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

@Title("DeleteProductController Specification")
@Narrative("The Specification of the behaviour of the DeleteProductController. It can delete a proudct from an order previously created")
@SpringBootTest
@AutoConfigureMockMvc
class DeleteProductControllerIT extends Specification {

    @Autowired
    private MockMvc mvc

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    private OrderRepository repository

    @Autowired
    private ProductRepository productRepository

    def "when delete is performed then the response has status 200 with order"() {
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
        repository.save(OrderFixture.anyOrder())
    }

    private Order getOrder() {
        repository.findAll().get(0)
    }

}

