package com.ecommerce.api.order.adapter.web

import static groovy.json.JsonOutput.toJson
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions

import com.ecommerce.api.order.domain.CustomerFixture
import com.ecommerce.api.order.domain.OrderFixture
import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.port.OrderRepository
import com.fasterxml.jackson.databind.ObjectMapper

import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

@Title("ShippingOrderController Specification")
@Narrative("The Specification of the behaviour of the ShippingOrderController. It can ship an order")
@SpringBootTest
@AutoConfigureMockMvc
class ShippingOrderControllerIT extends Specification {

    @Autowired
    private MockMvc mvc

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    private OrderRepository repository

    @DirtiesContext
    def "when post is performed then the response has status 201 with shipping label tracking number"() {
        given:
            createOrder()
            Order order = getOrder()
            order.addCustomer(CustomerFixture.anyCustomer())
            UUID orderId = order.id().value()
            Map request = [
                orderId: orderId,
            ]
        when:
            ResultActions result = mvc.perform(post("/shippings/$orderId")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(request)))
        then:
            result.andExpect(status().isCreated())
        and:
            with(objectMapper.readValue(result.andReturn().response.contentAsString, Map)) {
                it.trackingNumber == "ABCD-123456"
            }
    }

    private void createOrder() {
        repository.save(OrderFixture.anyOrder())
    }

    private Order getOrder() {
        repository.findAll().get(0)
    }

}
