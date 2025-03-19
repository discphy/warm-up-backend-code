package cleancode.studycafe.tobe.model.order;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafePassOrderTest {

    @DisplayName("사물함 패스가 없는 총 금액을 구한다.")
    @Test
    void getTotalPriceWithoutLockerPass() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 11000, 0.1);
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, null);

        // when
        int totalPrice = order.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(11000 - 1100);
    }

    @DisplayName("할인이 없는 총 금액을 구한다.")
    @Test
    void getTotalPriceWithoutDiscount() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 11000, 0);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 20000);
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, lockerPass);

        // when
        int totalPrice = order.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(11000 + 20000);
    }

    @DisplayName("총 금액을 구한다.")
    @Test
    void getTotalPrice() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 11000, 0.1);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 20000);
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, lockerPass);

        // when
        int totalPrice = order.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(11000 + 20000 - 1100);
    }

    @DisplayName("존재하지 않는 사물함 패스를 가져온다.")
    @Test
    void getLockerPassWithoutLockerPass() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 11000, 0.1);
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, null);

        // when
        Optional<StudyCafeLockerPass> result = order.getLockerPass();

        // then
        assertThat(result).isNotPresent();
    }

    @DisplayName("사물함 패스를 가져온다.")
    @Test
    void getLockerPass() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 11000, 0.1);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 20000);
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, lockerPass);

        // when
        Optional<StudyCafeLockerPass> result = order.getLockerPass();

        // then
        assertThat(result).isPresent().get().isEqualTo(lockerPass);
    }

}