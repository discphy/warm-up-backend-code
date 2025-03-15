package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafeSeatPassTest {

    @DisplayName("사물함 패스를 사용할 수 없는지 확인한다.")
    @ParameterizedTest
    @CsvSource({
        "HOURLY, true",
        "WEEKLY, true",
        "FIXED, false",
    })
    void cannotUseLocker(StudyCafePassType type, boolean expected) {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(type, 1, 10000, 0);

        // when
        boolean result = seatPass.cannotUseLocker();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("사물함 패스와 좌석 패스의 기간과 타입이 일치하는지 확인한다.")
    @ParameterizedTest
    @CsvSource({
        "HOURLY, 3, FIXED, 1, false", // 둘다 다를경우
        "HOURLY, 1, FIXED, 1, false", // type 다를경우
        "FIXED, 3, FIXED, 1, false", // duration 다를 경우
        "FIXED, 1, FIXED, 1, true", // 둘이 같을 경우
    })
    void isSameDurationType(StudyCafePassType lockerType,
                            int lockerDuration,
                            StudyCafePassType matchedType,
                            int matchedDuration,
                            boolean expected) {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(matchedType, matchedDuration, 10000, 0);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(lockerType, lockerDuration, 10000);

        // when
        boolean result = seatPass.isSameDurationType(lockerPass);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("주어진 타입과 좌석 패스의 타입이 일치하는지 확인한다.")
    @ParameterizedTest
    @CsvSource({
        "HOURLY, FIXED, false",
        "WEEKLY, FIXED, false",
        "FIXED, FIXED, true",
    })
    void isSamePassType(StudyCafePassType type, StudyCafePassType matchedType, boolean expected) {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(matchedType, 1, 10000, 0);

        // when
        boolean result = seatPass.isSamePassType(type);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("할인 가격을 가져온다.")
    @Test
    void getDiscountPrice() {
        // given
        int price = 10000;
        double discountRate = 0.1;

        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 1, price, discountRate);

        // when
        int discountPrice = seatPass.getDiscountPrice();

        // then
        assertThat(discountPrice).isEqualTo(1000);
    }

}