package com.ecommerce.api.order.application.usecase

import static java.time.LocalDate.now

import com.ecommerce.api.order.domain.OrderFixture
import com.ecommerce.api.order.domain.model.BusinessException
import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.model.customer.Address
import com.ecommerce.api.order.domain.model.customer.Customer
import com.ecommerce.api.order.domain.model.shipping.ShippingLabel
import com.ecommerce.api.order.domain.port.OrderRepository
import com.ecommerce.api.order.domain.port.ShippingRepository

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class ShipOrderTest extends Specification {

    OrderRepository orderRepository = Mock()

    ShippingRepository shippingRepository = Mock()

    @Subject
    ShipOrder shipOrder = new ShipOrder(orderRepository, shippingRepository)

    @Unroll
    def 'should ship an order with shipping label prefix #prefix if is #isSpain that the town is within Spain'() {
        given:
            Order order = OrderFixture.anyOrder()
            Customer customer = recipientCustomer
            order.addCustomer(customer)
            UUID orderId = UUID.randomUUID()
            ShippingLabel fastDeliveryShipping = new ShippingLabel("ABCD-123456", now())
            ShippingLabel standardDeliveryShipping = new ShippingLabel("ZYXW-123457", now())
        when:
            ShippingLabel result = shipOrder.execute(orderId)
            String trackingNumberPrefix = result.trackingNumber().substring(0, 4)
        then:
            trackingNumberPrefix == prefix
            1 * orderRepository.findById(_ as UUID) >> Optional.of(order)
            timesRequestFastDelivery * shippingRepository.requestFastDelivery(_ as Order) >> fastDeliveryShipping
            timesRequestStandardDelivery * shippingRepository.requestStandardDelivery(_ as Order) >> standardDeliveryShipping
        where:
            recipientCustomer            | isSpain || timesRequestFastDelivery || timesRequestStandardDelivery || prefix
            getSpanishCustomer("BCN")    | true    || 1                        || 0                            || "ABCD"
            getPortugueseCustomer("OPO") | false   || 0                        || 1                            || "ZYXW"
    }

    def 'should throw business exception when ship an order and OrderRepository returns null'() {
        given:
            UUID orderId = UUID.randomUUID()
        when:
            shipOrder.execute(orderId)
        then:
            1 * orderRepository.findById(_ as UUID) >> Optional.ofNullable(null)
            thrown(BusinessException)
    }

    private static Customer getSpanishCustomer(String town) {
        UUID id = UUID.randomUUID()
        Address address = new Address("Rue Percebe", 13, 28008, town)
        new Customer(id, "name", "surname", address)
    }

    private static Customer getPortugueseCustomer(String town) {
        UUID id = UUID.randomUUID()
        Address address = new Address("Rua", 15, 33380, town)
        new Customer(id, "name", "surname", address)
    }

}
