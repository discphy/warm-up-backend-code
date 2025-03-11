package cleancode.studycafe.tobe.model.pass.locker;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafeLockerPassTest {

    @DisplayName("주어진 타입과 사물함 패스의 타입이 일치하는지 확인한다.")
    @ParameterizedTest
    @CsvSource({
        "HOURLY, FIXED, false",
        "WEEKLY, FIXED, false",
        "FIXED, FIXED, true",
    })
    void isSamePassType(StudyCafePassType type, StudyCafePassType matchedType, boolean expected) {
        // given
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(matchedType, 1, 10000);

        // when
        boolean result = lockerPass.isSamePassType(type);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("주어진 타입과 사물함 패스의 타입이 일치하는지 확인한다.")
    @ParameterizedTest
    @CsvSource({
        "4, 12, false",
        "12, 12, true",
    })
    void isSameDuration(int duration, int matchedDuration, boolean expected) {
        // given
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, matchedDuration, 10000);

        // when
        boolean result = lockerPass.isSameDuration(duration);

        // then
        assertThat(result).isEqualTo(expected);
    }

}