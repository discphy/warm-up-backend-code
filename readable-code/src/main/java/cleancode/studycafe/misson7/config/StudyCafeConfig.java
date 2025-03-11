package cleancode.studycafe.misson7.config;

import cleancode.studycafe.misson7.io.handler.InputHandler;
import cleancode.studycafe.misson7.io.handler.OutputHandler;
import cleancode.studycafe.misson7.provider.LockerPassProvider;
import cleancode.studycafe.misson7.provider.SeatPassProvider;

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
