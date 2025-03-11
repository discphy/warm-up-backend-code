package cleancode.studycafe.tobe.model.pass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafeSeatPassesTest {

    @DisplayName("타입으로 좌석권을 찾는다.")
    @Test
    void findPassBy() {
        // given
        List<StudyCafeSeatPass> list = List.of(
            StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 11000, 0.1),
            StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 4, 11000, 0.1),
            StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 11000, 0.1),
            StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 11000, 0.1),
            StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 8, 11000, 0.1),
            StudyCafeSeatPass.of(StudyCafePassType.FIXED, 10, 11000, 0.1)
        );
        StudyCafeSeatPasses seatPasses = StudyCafeSeatPasses.of(list);

        // when
        List<StudyCafeSeatPass> result = seatPasses.findPassBy(StudyCafePassType.FIXED);

        // then
        assertThat(result).hasSize(4);
        assertThat(result).extracting(StudyCafeSeatPass::getPassType).allMatch(StudyCafePassType.FIXED::equals);
    }

    @DisplayName("존재하지 않는 타입으로 좌석권을 찾는다.")
    @Test
    void notFoundPassBy() {
        // given
        List<StudyCafeSeatPass> list = List.of(
            StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 11000, 0.1),
            StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 4, 11000, 0.1),
            StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 11000, 0.1),
            StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 11000, 0.1),
            StudyCafeSeatPass.of(StudyCafePassType.FIXED, 10, 11000, 0.1)
        );
        StudyCafeSeatPasses seatPasses = StudyCafeSeatPasses.of(list);

        // when
        List<StudyCafeSeatPass> result = seatPasses.findPassBy(StudyCafePassType.HOURLY);

        // then
        assertThat(result).isEmpty();
    }

}