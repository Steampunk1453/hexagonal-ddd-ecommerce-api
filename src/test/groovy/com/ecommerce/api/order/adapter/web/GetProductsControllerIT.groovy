package com.ecommerce.api.order.adapter.web

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions

import com.ecommerce.api.order.domain.model.Product
import com.ecommerce.api.order.domain.port.ProductRepository
import com.jayway.jsonpath.JsonPath

import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

@Title("GetProductsController Specification")
@Narrative("The Specification of the behaviour of the GetProductsController. It can get all products previously created")
@SpringBootTest
@AutoConfigureMockMvc
class GetProductsControllerIT extends Specification {

    @Autowired
    private MockMvc mvc

    @Autowired
    private ProductRepository productRepository

    @DirtiesContext
    def "when get is performed then the response has status 200 with products"() {
        given:
            Product product = getProduct()
        when:
            ResultActions result = mvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON))
        then:
            result.andExpect(status().isOk())
        and:
            with(JsonPath.read(result.andReturn().response.getContentAsString(), '[0]')) {
                it["id"] == product.id().toString()
                it["code"] == product.code().toString()
                it["description"] == product.description().toString()
            }
    }

    private Product getProduct() {
        productRepository.findAll().get(0)
    }

}
