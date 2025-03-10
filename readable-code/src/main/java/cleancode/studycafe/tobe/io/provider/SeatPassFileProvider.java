package cleancode.studycafe.tobe.io.provider;

import cleancode.studycafe.tobe.io.provider.support.CsvFileReadSupport;
import cleancode.studycafe.tobe.model.pass.seat.StudyCafeSeatPasses;
import cleancode.studycafe.tobe.model.provider.ReadSeatPasses;
import cleancode.studycafe.tobe.provider.SeatPassProvider;

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
            throw new RuntimeException("파일을 읽는데 실패하였습니다.", e);
        }
    }
}
