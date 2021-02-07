package com.ecommerce.api.order.adapter.web

import static groovy.json.JsonOutput.toJson
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions

import com.ecommerce.api.order.domain.OrderFixture
import com.ecommerce.api.order.domain.port.OrderRepository
import com.ecommerce.api.order.domain.port.ProductRepository

import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

@Title("AddProductController Specification")
@Narrative("The Specification of the behaviour of the AddProductController. It can add a product to a given order")
@SpringBootTest
@AutoConfigureMockMvc
class AddProductControllerIT extends Specification {

    @Autowired
    private MockMvc mvc

    @Autowired
    private OrderRepository orderRepository

    @Autowired
    private ProductRepository productRepository

    def "when post is performed then the response has status 200"() {
        given:
        createOrder()
        UUID orderId = getOrderId()
        UUID productId = getProductId()
        Map request = [
                orderId  : orderId,
                productId: productId,
                quantity : 1
        ]
        when:
        ResultActions result = mvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
        then:
        result.andExpect(status().isOk())
        and:
        result.andReturn().response.contentAsString != null

    }

    private void createOrder() {
        orderRepository.save(OrderFixture.anyOrder())
    }

    private UUID getOrderId() {
        orderRepository.findAll().get(0).id().value()
    }

    private UUID getProductId() {
        productRepository.findAll().get(0).id()
    }

}
