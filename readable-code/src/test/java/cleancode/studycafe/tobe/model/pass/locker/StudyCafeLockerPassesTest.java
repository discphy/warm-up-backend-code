package cleancode.studycafe.tobe.model.pass.locker;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafeLockerPassesTest {

    @DisplayName("좌석 패스로 기간과 타입이 동일한 사물함 패스를 찾는다.")
    @Test
    void findLockerPassBy() {
        // given
        List<StudyCafeLockerPass> list = lockerPassList();
        StudyCafeLockerPasses lockerPasses = StudyCafeLockerPasses.of(list);

        // when
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 20000, 0.1);
        Optional<StudyCafeLockerPass> optionalLockerPass = lockerPasses.findLockerPassBy(seatPass);

        // then
        assertThat(optionalLockerPass).isNotEmpty();

        StudyCafeLockerPass findLockerPass = optionalLockerPass.get();
        assertThat(findLockerPass.getPassType()).isEqualTo(StudyCafePassType.FIXED);
        assertThat(findLockerPass.getDuration()).isEqualTo(4);
        assertThat(findLockerPass.getPrice()).isEqualTo(11000);
    }

    @DisplayName("좌석 패스와 기간과 타입이 동일한 사물함 패스를 찾지 못한다.")
    @Test
    void notFoundLockerPassBy() {
        // given
        List<StudyCafeLockerPass> list = lockerPassList();
        StudyCafeLockerPasses lockerPasses = StudyCafeLockerPasses.of(list);

        // when
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 3, 20000, 0.1);
        Optional<StudyCafeLockerPass> optionalLockerPass = lockerPasses.findLockerPassBy(seatPass);

        // then
        assertThat(optionalLockerPass).isEmpty();
    }

    private List<StudyCafeLockerPass> lockerPassList() {
        return List.of(
            StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 11000),
            StudyCafeLockerPass.of(StudyCafePassType.WEEKLY, 4, 17000),
            StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 11000),
            StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 18000),
            StudyCafeLockerPass.of(StudyCafePassType.HOURLY, 8, 11000),
            StudyCafeLockerPass.of(StudyCafePassType.FIXED, 10, 11000)
        );
    }

}