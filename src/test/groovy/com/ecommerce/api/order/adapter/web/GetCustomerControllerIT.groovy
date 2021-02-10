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

import com.ecommerce.api.order.domain.CustomerFixture
import com.ecommerce.api.order.domain.model.customer.Customer
import com.ecommerce.api.order.domain.port.CustomerRepository
import com.fasterxml.jackson.databind.ObjectMapper

import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

@Title("GetCustomerController Specification")
@Narrative("The Specification of the behaviour of the GetCustomerController. It can get an order previously created")
@SpringBootTest
@AutoConfigureMockMvc
class GetCustomerControllerIT extends Specification {

    @Autowired
    private MockMvc mvc

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    private CustomerRepository repository

    @DirtiesContext
    def "when get is performed then the response has status 200 with customer"() {
        given:
            createCustomer()
            Customer customer = getCustomer()
            UUID customerId = customer.id()
        when:
            ResultActions result = mvc.perform(get("/customers/$customerId")
                    .contentType(MediaType.APPLICATION_JSON))
        then:
            result.andExpect(status().isOk())
        and:
            with(objectMapper.readValue(result.andReturn().response.contentAsString, Map)) {
                it.id == customer.id().toString()
                it.name == customer.name()
                it.surname == customer.surname()
                it.address["street"] == customer.address().street()
                it.address["number"] == customer.address().number()
                it.address["cp"] == customer.address().cp()
                it.address["town"] == customer.address().town()
            }
    }

    private void createCustomer() {
        repository.save(CustomerFixture.anyCustomer())
    }

    private Customer getCustomer() {
        repository.findAll().get(0)
    }

}
