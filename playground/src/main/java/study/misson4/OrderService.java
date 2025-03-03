package study.misson4;

import study.misson4.domain.Order;

import java.util.logging.Logger;

public class OrderService {

    public boolean validateOrder(Order order) {
        return order.validate();
    }
}
