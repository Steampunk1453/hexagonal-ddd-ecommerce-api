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

import com.ecommerce.api.order.domain.port.ProductRepository

import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

@Title("CreateOrderController Specification")
@Narrative("The Specification of the behaviour of the CreateOrderController. It can create an order")
@SpringBootTest
@AutoConfigureMockMvc
class CreateOrderControllerIT extends Specification {

    @Autowired
    private MockMvc mvc

    @Autowired
    private ProductRepository productRepository

    def "when post is performed then the response has status 201 and is not null"() {
        given:
            UUID productId = getProductId()
            Map request = [
                    productId: productId,
                    quantity : 1
            ]
        when:
        ResultActions result = mvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
        then:
        result.andExpect(status().isCreated())
        and:
        result.andReturn().response.contentAsString != null
    }

    private UUID getProductId() {
        productRepository.findAll().get(0).id()
    }

}
