package com.ecommerce.api.order.adapter.shipping;

import static java.time.LocalDate.now;

import org.springframework.stereotype.Repository;

import com.ecommerce.api.order.domain.model.Order;
import com.ecommerce.api.order.domain.model.shipping.ShippingLabel;
import com.ecommerce.api.order.domain.port.ShippingRepository;

@Repository
public class ZelerisShippingRepository implements ShippingRepository {

    @Override
    public ShippingLabel requestFastDelivery(Order order) {
        // call Zeleris service
        return new ShippingLabel("ABCD-123456", now());
    }

    @Override
    public ShippingLabel requestStandardDelivery(Order order) {
        // call Zeleris service
        return new ShippingLabel("ZYXW-123457", now());
    }
}
