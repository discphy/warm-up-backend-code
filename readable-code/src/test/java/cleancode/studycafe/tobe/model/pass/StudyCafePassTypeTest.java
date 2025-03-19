package cleancode.studycafe.tobe.model.pass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafePassTypeTest {

    @DisplayName("타입이 사물함 패스 타입인지 확인한다.")
    @ParameterizedTest
    @CsvSource({
        "HOURLY, false",
        "WEEKLY, false",
        "FIXED, true",
    })
    void isLockerType(StudyCafePassType type, boolean expected) {
        // when
        boolean result = type.isLockerType();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("타입이 사물함 패스 타입이 아닌지 확인한다.")
    @ParameterizedTest
    @CsvSource({
        "HOURLY, true",
        "WEEKLY, true",
        "FIXED, false",
    })
    void isNotLockerType(StudyCafePassType type, boolean expected) {
        // when
        boolean result = type.isNotLockerType();

        // then
        assertThat(result).isEqualTo(expected);
    }
}