package com.ecommerce.api.order.adapter.web

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
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
import com.fasterxml.jackson.databind.ObjectMapper

import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

@Title("GetTotalPriceController Specification")
@Narrative("The Specification of the behaviour of the GetTotalPriceController. It can get total price from an order")
@SpringBootTest
@AutoConfigureMockMvc
class GetTotalPriceControllerIT extends Specification {

    @Autowired
    private MockMvc mvc

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    private OrderRepository repository

    def "when get is performed then the response has status 200 with order total price"() {
        given:
        createOrder()
        Order order = getOrder()
        UUID orderId = order.id().value()
        when:
        ResultActions result = mvc.perform(get("/orders/price/$orderId")
                .contentType(MediaType.APPLICATION_JSON))
        then:
        result.andExpect(status().isOk())
        and:
        result.andReturn().response.contentAsString == order.totalPrice().toString()
    }

    private void createOrder() {
        repository.save(OrderFixture.anyOrder())
    }

    private Order getOrder() {
        repository.findAll().get(0)
    }

}
