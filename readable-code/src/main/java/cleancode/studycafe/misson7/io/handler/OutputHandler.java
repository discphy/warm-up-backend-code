package cleancode.studycafe.misson7.io.handler;

import cleancode.studycafe.misson7.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.misson7.model.pass.order.StudyCafePassOrder;
import cleancode.studycafe.misson7.model.pass.seat.StudyCafeSeatPasses;

public interface OutputHandler {

    void showWelcomeMessage();

    void showAnnouncement();

    void askPassTypeSelection();

    void showSeatPassListForSelection(StudyCafeSeatPasses seatPasses);

    void askLockerPass(StudyCafeLockerPass lockerPass);

    void showPassOrderSummary(StudyCafePassOrder order);

    void showSimpleMessage(String message);

}
