package com.ecommerce.api.order.adapter.web

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
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
import com.ecommerce.api.order.domain.model.customer.Customer
import com.ecommerce.api.order.domain.port.CustomerRepository
import com.ecommerce.api.order.domain.port.OrderRepository

import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

@Title("DeleteCustomerController Specification")
@Narrative("The Specification of the behaviour of DeleteCustomerController. It can delete a customer from an order previously created")
@SpringBootTest
@AutoConfigureMockMvc
class DeleteCustomerControllerIT extends Specification {

    @Autowired
    private MockMvc mvc

    @Autowired
    private CustomerRepository customerRepository

    @Autowired
    private OrderRepository orderRepository

    @DirtiesContext
    def "when delete is performed then the response has status 200 with order"() {
        given:
            createCustomer()
            Customer customer = getCustomer()
            UUID customerId = customer.id()
            createOrder()
            Order order = getOrder()
            UUID orderId = order.id().value()
        when:
            ResultActions result = mvc.perform(delete("/customers/$customerId/$orderId")
                    .contentType(MediaType.APPLICATION_JSON))
        then:
            result.andExpect(status().isOk())
    }

    private void createCustomer() {
        customerRepository.save(CustomerFixture.anyCustomer())
    }

    private Customer getCustomer() {
        customerRepository.findAll().get(0)
    }

    private void createOrder() {
        orderRepository.save(OrderFixture.anyOrder())
    }

    private Order getOrder() {
        orderRepository.findAll().get(0)
    }

}

