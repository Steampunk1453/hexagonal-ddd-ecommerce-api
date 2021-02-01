package com.ecommerce.api.order.adapter.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

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

    def "when get is performed then the response has status 200 and content is a list of orders"() {
        expect: "Status is 400"
            mvc.perform(MockMvcRequestBuilders.get("/orders"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .response.contentAsString.size()
    }

}
