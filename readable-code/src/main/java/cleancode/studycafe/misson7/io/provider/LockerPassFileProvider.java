package cleancode.studycafe.misson7.io.provider;

import cleancode.studycafe.misson7.exception.ProvideException;
import cleancode.studycafe.misson7.io.provider.support.CsvFileReadSupport;
import cleancode.studycafe.misson7.model.pass.locker.StudyCafeLockerPasses;
import cleancode.studycafe.misson7.model.provider.ReadLockerPasses;
import cleancode.studycafe.misson7.provider.LockerPassProvider;

import java.io.IOException;
import java.util.List;

public class LockerPassFileProvider implements LockerPassProvider {

    public static final String CSV_FILE_NAME = "locker.csv";

    @Override
    public StudyCafeLockerPasses provide() {
        try {
            List<String> allLines = CsvFileReadSupport.readAllLinesBy(CSV_FILE_NAME);
            ReadLockerPasses readSeatPasses = ReadLockerPasses.ofLines(allLines);

            return readSeatPasses.toPasses();
        } catch (IOException e) {
            throw new ProvideException(e.getMessage());
        }
    }
}
