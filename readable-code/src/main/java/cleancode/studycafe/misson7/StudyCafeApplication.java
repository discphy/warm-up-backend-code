package cleancode.studycafe.misson7;

import cleancode.studycafe.misson7.config.StudyCafeConfig;
import cleancode.studycafe.misson7.io.handler.ConsoleInputHandler;
import cleancode.studycafe.misson7.io.handler.ConsoleOutputHandler;
import cleancode.studycafe.misson7.io.provider.LockerPassFileProvider;
import cleancode.studycafe.misson7.io.provider.SeatPassFileProvider;

public class StudyCafeApplication {

    public static void main(String[] args) {
        StudyCafeConfig config = new StudyCafeConfig(
            new ConsoleInputHandler(),
            new ConsoleOutputHandler(),
            new SeatPassFileProvider(),
            new LockerPassFileProvider()
        );

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(config);
        studyCafePassMachine.run();
    }

}
