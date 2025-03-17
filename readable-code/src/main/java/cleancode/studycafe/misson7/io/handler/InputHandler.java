package cleancode.studycafe.misson7.io.handler;

import cleancode.studycafe.misson7.model.pass.StudyCafePassType;
import cleancode.studycafe.misson7.model.pass.seat.StudyCafeSeatPass;
import cleancode.studycafe.misson7.model.pass.seat.StudyCafeSeatPasses;

public interface InputHandler {

    StudyCafePassType getPassTypeSelectingUserAction();

    StudyCafeSeatPass getSelectSeatPass(StudyCafeSeatPasses seatPasses);

    boolean getLockerSelection();

}
