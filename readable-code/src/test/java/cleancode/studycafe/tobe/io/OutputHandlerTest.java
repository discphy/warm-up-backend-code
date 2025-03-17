package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.order.StudyCafePassOrder;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OutputHandlerTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @DisplayName("선택하기 위한 좌석 패스 목록을 출력한다.")
    @Test
    void showPassListForSelection() {
        // given
        OutputHandler outputHandler = new OutputHandler();
        List<StudyCafeSeatPass> seatPasses = seatPassList();

        // when
        outputHandler.showPassListForSelection(seatPasses);

        // then
        String output = outputStream.toString().trim();
        assertThat(output).contains(
            "1. 4주권 - 11000원",
            "2. 4주권 - 11000원",
            "3. 12주권 - 11000원",
            "4. 4주권 - 11000원",
            "5. 10시간권 - 11000원"
        );
    }

    @DisplayName("사물함 패스를 이용할 지에 대해 출력한다.")
    @Test
    void askLockerPass() {
        // given
        OutputHandler outputHandler = new OutputHandler();
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 20000);

        // when
        outputHandler.askLockerPass(lockerPass);

        // then
        String output = outputStream.toString().trim();
        assertThat(output).contains(
            "4주권 - 20000원"
        );
    }

    @DisplayName("주문 내용을 출력한다.")
    @Test
    void showPassOrderSummary() {
        // given
        OutputHandler outputHandler = new OutputHandler();
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 11000, 0.1);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 20000);
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, lockerPass);

        // when
        outputHandler.showPassOrderSummary(order);

        // then
        String output = outputStream.toString().trim();
        assertThat(output).contains(
            "이용권: 4주권 - 11000원",
            "사물함: 12주권 - 20000원",
            "이벤트 할인 금액: 1100원",
            "총 결제 금액: 29900원"
        );
    }

    private List<StudyCafeSeatPass> seatPassList() {
        return List.of(
            StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 11000, 0.1),
            StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 4, 11000, 0.1),
            StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 11000, 0.1),
            StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 11000, 0.1),
            StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 10, 11000, 0.1)
        );
    }
}