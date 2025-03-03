package study.misson4;

import study.misson4.domain.Order;

import java.util.logging.Logger;

public class OrderService {

    private static final Logger log = Logger.getLogger(OrderService.class.getName());

    public boolean validateOrder(Order order) {
        if (order.notExistItems()) {
            log.info("주문 항목이 없습니다.");
            return false;
        }

        if (order.invalidTotalPrice()) {
            return false;
        }

        if (!order.hasCustomerInfo()) {
            log.info("사용자 정보가 없습니다.");
            return false;
        }

        return true;
    }
}
