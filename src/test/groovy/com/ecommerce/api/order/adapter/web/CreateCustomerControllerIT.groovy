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

import com.ecommerce.api.order.adapter.web.request.AddressRequest
import com.ecommerce.api.order.domain.OrderFixture
import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.model.customer.PersonalData
import com.ecommerce.api.order.domain.port.OrderRepository

import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

@Title("CreateCustomerController Specification")
@Narrative("The Specification of the behaviour of the CreateCustomerController. It can create a customer")
@SpringBootTest
@AutoConfigureMockMvc
class CreateCustomerControllerIT extends Specification {

    @Autowired
    private MockMvc mvc

    @Autowired
    private OrderRepository repository

    @DirtiesContext
    def "when post is performed then the response has status 201 and is not null"() {
        given:
            createOrder()
            UUID orderId = getOrder().id().value()
            PersonalData personalData = new PersonalData("name", "surname")
            AddressRequest address = new AddressRequest("Rue Percebe", 13, 28008, "Madrid")
            Map request = [
                    personalData: personalData,
                    address: address,
                    orderId: orderId
            ]
        when:
            ResultActions result = mvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(request)))
        then:
            result.andExpect(status().isCreated())
    }

    private void createOrder() {
        repository.save(OrderFixture.anyOrder())
    }

    private Order getOrder() {
        repository.findAll().get(0)
    }

}
