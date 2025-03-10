package cleancode.studycafe.tobe.io.handler;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.seat.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.seat.StudyCafeSeatPasses;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public StudyCafePassType getPassTypeSelectingUserAction() {
        String userInput = SCANNER.nextLine();
        return StudyCafePassType.findBy(userInput);
    }

    @Override
    public StudyCafeSeatPass getSelectSeatPass(StudyCafeSeatPasses seatPasses) {
        String userInput = SCANNER.nextLine();
        int selectedIndex = Integer.parseInt(userInput) - 1;
        return seatPasses.findBy(selectedIndex);
    }

    @Override
    public boolean getLockerSelection() {
        String userInput = SCANNER.nextLine();
        return "1".equals(userInput);
    }

}
