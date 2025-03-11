package cleancode.studycafe.misson7.io.provider;

import cleancode.studycafe.misson7.exception.ProvideException;
import cleancode.studycafe.misson7.io.provider.support.CsvFileReadSupport;
import cleancode.studycafe.misson7.model.pass.seat.StudyCafeSeatPasses;
import cleancode.studycafe.misson7.model.provider.ReadSeatPasses;
import cleancode.studycafe.misson7.provider.SeatPassProvider;

import java.io.IOException;
import java.util.List;

public class SeatPassFileProvider implements SeatPassProvider {

    public static final String CSV_FILE_NAME = "pass-list.csv";

    @Override
    public StudyCafeSeatPasses provide() {
        try {
            List<String> allLines = CsvFileReadSupport.readAllLinesBy(CSV_FILE_NAME);
            ReadSeatPasses readSeatPasses = ReadSeatPasses.ofLines(allLines);

            return readSeatPasses.toPasses();
        } catch (IOException e) {
            throw new ProvideException(e.getMessage());
        }
    }
}
