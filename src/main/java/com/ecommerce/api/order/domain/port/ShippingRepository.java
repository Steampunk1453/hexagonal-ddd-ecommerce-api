package com.ecommerce.api.order.domain.port;

import com.ecommerce.api.order.domain.model.Order;
import com.ecommerce.api.order.domain.model.shipping.ShippingLabel;

public interface ShippingRepository {

    ShippingLabel requestFastDelivery(Order order);

    ShippingLabel requestStandardDelivery(Order order);
}
