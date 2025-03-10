package cleancode.studycafe.tobe.io.handler;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.pass.order.StudyCafePassOrder;
import cleancode.studycafe.tobe.model.pass.seat.StudyCafeSeatPasses;

public interface OutputHandler {

    void showWelcomeMessage();

    void showAnnouncement();

    void askPassTypeSelection();

    void showSeatPassListForSelection(StudyCafeSeatPasses seatPasses);

    void askLockerPass(StudyCafeLockerPass lockerPass);

    void showPassOrderSummary(StudyCafePassOrder order);

    void showSimpleMessage(String message);

}
