package cleancode.studycafe.tobe.io.provider;

import cleancode.studycafe.tobe.io.provider.support.CsvFileReadSupport;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPasses;
import cleancode.studycafe.tobe.model.provider.ReadLockerPasses;
import cleancode.studycafe.tobe.provider.LockerPassProvider;

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
            throw new RuntimeException("파일을 읽는데 실패하였습니다.", e);
        }
    }
}
