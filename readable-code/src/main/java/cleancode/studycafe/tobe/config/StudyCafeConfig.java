package cleancode.studycafe.tobe.config;

import cleancode.studycafe.tobe.io.handler.InputHandler;
import cleancode.studycafe.tobe.io.handler.OutputHandler;
import cleancode.studycafe.tobe.provider.LockerPassProvider;
import cleancode.studycafe.tobe.provider.SeatPassProvider;

public class StudyCafeConfig {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final SeatPassProvider seatPassProvider;
    private final LockerPassProvider lockerPassProvider;

    public StudyCafeConfig(InputHandler inputHandler,
                           OutputHandler outputHandler,
                           SeatPassProvider seatPassProvider,
                           LockerPassProvider lockerPassProvider) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.seatPassProvider = seatPassProvider;
        this.lockerPassProvider = lockerPassProvider;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public OutputHandler getOutputHandler() {
        return outputHandler;
    }

    public SeatPassProvider getSeatPassProvider() {
        return seatPassProvider;
    }

    public LockerPassProvider getLockerPassProvider() {
        return lockerPassProvider;
    }

}
