package cleancode.studycafe.tobe.provider;

import cleancode.studycafe.tobe.io.provider.LockerPassFileReader;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPasses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

class LockerPassProviderTest {

    @DisplayName("파일을 읽을 수 없으면 사물함 컬렉션을 가져오지 못한다.")
    @Test
    void getLockerPassesWithUnReadableFile() {
        try (MockedStatic<Files> mockedFiles = mockStatic(Files.class)) {
            // given
            mockedFiles.when(() -> Files.readAllLines(any()))
                .thenThrow(new IOException());

            LockerPassProvider provider = new LockerPassFileReader();

            // when & then
            assertThatThrownBy(provider::getLockerPasses)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("파일을 읽는데 실패했습니다.");
        }
    }

    @DisplayName("파일을 읽어서 사물함권을 가져온다.")
    @Test
    void getLockerPasses() {
        try (MockedStatic<Files> mockedFiles = mockStatic(Files.class)) {
            // given
            mockedFiles.when(() -> Files.readAllLines(any()))
                .thenReturn(List.of(
                    "FIXED,4,10000",
                    "FIXED,12,30000"
                ));

            LockerPassProvider provider = new LockerPassFileReader();

            // when
            StudyCafeLockerPasses lockerPasses = provider.getLockerPasses();

            StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 20000, 0.1);
            Optional<StudyCafeLockerPass> optionalLockerPass = lockerPasses.findLockerPassBy(seatPass);

            // then
            assertThat(optionalLockerPass).isNotEmpty();

            StudyCafeLockerPass lockerPass = optionalLockerPass.get();
            assertThat(lockerPass.getPassType()).isEqualTo(StudyCafePassType.FIXED);
            assertThat(lockerPass.getDuration()).isEqualTo(4);
            assertThat(lockerPass.getPrice()).isEqualTo(10000);
        }
    }

}