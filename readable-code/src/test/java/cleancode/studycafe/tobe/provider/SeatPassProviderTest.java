package cleancode.studycafe.tobe.provider;

import cleancode.studycafe.tobe.io.provider.SeatPassFileReader;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPasses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

class SeatPassProviderTest {

    @DisplayName("파일을 읽을 수 없으면 좌석권 컬렉션을 가져오지 못한다.")
    @Test
    void getSeatPassesWithUnReadableFile() {
        try (MockedStatic<Files> mockedFiles = mockStatic(Files.class)) {
            // given
            mockedFiles.when(() -> Files.readAllLines(any()))
                .thenThrow(new IOException());

            SeatPassProvider provider = new SeatPassFileReader();

            // when & then
            assertThatThrownBy(provider::getSeatPasses)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("파일을 읽는데 실패했습니다.");
        }
    }

    @DisplayName("파일을 읽어서 좌석권을 가져온다.")
    @Test
    void getSeatPasses() {
        try (MockedStatic<Files> mockedFiles = mockStatic(Files.class)) {
            // given
            mockedFiles.when(() -> Files.readAllLines(any()))
                .thenReturn(List.of(
                    "WEEKLY,2,4000,0.0",
                    "WEEKLY,12,120000,0.3",
                    "HOURLY,4,6500,0.1"
                ));

            SeatPassProvider provider = new SeatPassFileReader();

            // when
            StudyCafeSeatPasses seatPasses = provider.getSeatPasses();

            // then
            List<StudyCafeSeatPass> fixedSeatPasses = seatPasses.findPassBy(StudyCafePassType.FIXED);
            List<StudyCafeSeatPass> hourlySeatPasses = seatPasses.findPassBy(StudyCafePassType.HOURLY);
            List<StudyCafeSeatPass> weeklySeatPasses = seatPasses.findPassBy(StudyCafePassType.WEEKLY);
            assertThat(fixedSeatPasses).isEmpty();
            assertThat(hourlySeatPasses).hasSize(1)
                .extracting("passType", "duration", "price", "discountRate")
                .containsExactly(
                    tuple(StudyCafePassType.HOURLY, 4, 6500, 0.1)
                );
            assertThat(weeklySeatPasses).hasSize(2)
                .extracting("passType", "duration", "price", "discountRate")
                .containsExactly(
                    tuple(StudyCafePassType.WEEKLY, 2, 4000, 0.0),
                    tuple(StudyCafePassType.WEEKLY, 12, 120000, 0.3)
                );
        }
    }

}