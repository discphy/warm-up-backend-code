package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.config.StudyCafeConfig;
import cleancode.studycafe.tobe.io.handler.ConsoleInputHandler;
import cleancode.studycafe.tobe.io.handler.ConsoleOutputHandler;
import cleancode.studycafe.tobe.io.provider.LockerPassFileProvider;
import cleancode.studycafe.tobe.io.provider.SeatPassFileProvider;

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
