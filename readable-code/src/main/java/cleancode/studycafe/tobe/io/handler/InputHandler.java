package cleancode.studycafe.tobe.io.handler;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.seat.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.seat.StudyCafeSeatPasses;

public interface InputHandler {

    StudyCafePassType getPassTypeSelectingUserAction();

    StudyCafeSeatPass getSelectSeatPass(StudyCafeSeatPasses seatPasses);

    boolean getLockerSelection();

}
