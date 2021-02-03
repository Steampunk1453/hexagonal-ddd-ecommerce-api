package com.ecommerce.api.order.adapter.web

import static groovy.json.JsonOutput.toJson
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc

import com.ecommerce.api.order.adapter.web.request.CreateOrderRequest

import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

@Title("GetOrdersController Specification")
@Narrative("The Specification of the behaviour of the GetOrdersController. It can get all orders")
@SpringBootTest
@AutoConfigureMockMvc
class GetOrdersControllerIT extends Specification {

    @Autowired
    private MockMvc mvc

    def "when post is performed then the response has status 201"() {
        given:
            Map request = [
                productId: UUID.randomUUID().toString(),
                quantity : 1
            ]
        when:
            def results = mvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
        then:
            results.andExpect(status().isCreated())
    }

//    def "when get is performed then the response has status 200 and content is a list of orders"() {
//        expect: "Status is 400"
//            mvc.perform(MockMvcRequestBuilders.get("/orders")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(buildOrderRequest()))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn()
//                .response.contentAsString.size()
//    }

    private static buildOrderRequest() {
        return new CreateOrderRequest(UUID.randomUUID(), 1)
    }

}
