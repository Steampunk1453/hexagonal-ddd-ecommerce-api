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

@Title("GetOrderController Specification")
@Narrative("The Specification of the behaviour of the GetOrderController. It can get an order previously created")
@SpringBootTest
@AutoConfigureMockMvc
class GetOrderControllerIT extends Specification {

    @Autowired
    private MockMvc mvc

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    private OrderRepository repository

    def "when get is performed then the response has status 200 with order"() {
        given:
        createOrder()
        Order order = getOrder()
        UUID orderId = order.id().value()
        when:
        ResultActions result = mvc.perform(get("/orders/$orderId")
                .contentType(MediaType.APPLICATION_JSON))
        then:
        result.andExpect(status().isOk())
        and:
        with(objectMapper.readValue(result.andReturn().response.contentAsString, Map)) {
            it.id == order.id().value().toString()
            it.orderItems[0]["product"]["code"] == order.orderItems().get(0).product().code()
            it.orderItems[0]["product"]["description"] == order.orderItems().get(0).product().description()
                it.orderItems[0]["product"]["price"] == order.orderItems().get(0).product().price()
                it.orderItems[0]["price"] == order.orderItems().get(0).price()
                it.orderItems[0]["quantity"] == order.orderItems().get(0).quantity()
                it.totalPrice == order.totalPrice()
        }
    }

    private void createOrder() {
        repository.save(OrderFixture.anyOrder())
    }

    private Order getOrder() {
        repository.findAll().get(0)
    }

}
