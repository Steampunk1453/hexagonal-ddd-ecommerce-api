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

@Title("DeleteOrderController Specification")
@Narrative("The Specification of the behaviour of the DeleteOrderController. It can delete an order previously created")
@SpringBootTest
@AutoConfigureMockMvc
class DeleteOrderControllerIT extends Specification {

    @Autowired
    private MockMvc mvc

    @Autowired
    private OrderRepository repository

    @DirtiesContext
    def "when delete is performed then the response has status 200"() {
        given:
            createOrder()
            Order order = getOrder()
            UUID orderId = order.id().value()
        when:
            ResultActions result = mvc.perform(delete("/orders/$orderId")
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

