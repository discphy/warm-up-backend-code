package cleancode.studycafe.tobe.model.provider;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPasses;

import java.util.List;

import static cleancode.studycafe.tobe.io.provider.support.CsvFileReadSupport.CSV_SPLITTER;

public class ReadLockerPasses {

    private final List<StudyCafeLockerPass> passes;

    private ReadLockerPasses(List<StudyCafeLockerPass> passes) {
        this.passes = passes;
    }

    public static ReadLockerPasses ofLines(List<String> lines) {
        List<StudyCafeLockerPass> passes = lines.stream()
            .map(ReadLockerPasses::ofLine)
            .toList();

        return new ReadLockerPasses(passes);
    }

    private static StudyCafeLockerPass ofLine(String line) {
        String[] values = line.split(CSV_SPLITTER);

        StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
        int duration = Integer.parseInt(values[1]);
        int price = Integer.parseInt(values[2]);

        return StudyCafeLockerPass.of(studyCafePassType, duration, price);
    }

    public StudyCafeLockerPasses toPasses() {
        return StudyCafeLockerPasses.of(passes);
    }

}
