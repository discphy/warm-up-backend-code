package cleancode.studycafe.misson7.model.provider;

import cleancode.studycafe.misson7.model.pass.StudyCafePassType;
import cleancode.studycafe.misson7.model.pass.seat.StudyCafeSeatPass;
import cleancode.studycafe.misson7.model.pass.seat.StudyCafeSeatPasses;

import java.util.List;

import static cleancode.studycafe.misson7.io.provider.support.CsvFileReadSupport.CSV_SPLITTER;

public class ReadSeatPasses {

    private final List<StudyCafeSeatPass> passes;

    private ReadSeatPasses(List<StudyCafeSeatPass> passes) {
        this.passes = passes;
    }

    public static ReadSeatPasses ofLines(List<String> lines) {
        List<StudyCafeSeatPass> passes = lines.stream()
            .map(ReadSeatPasses::ofLine)
            .toList();

        return new ReadSeatPasses(passes);
    }

    private static StudyCafeSeatPass ofLine(String line) {
        String[] values = line.split(CSV_SPLITTER);

        StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
        int duration = Integer.parseInt(values[1]);
        int price = Integer.parseInt(values[2]);
        double discountRate = Double.parseDouble(values[3]);

        return StudyCafeSeatPass.of(studyCafePassType, duration, price, discountRate);
    }

    public StudyCafeSeatPasses toPasses() {
        return StudyCafeSeatPasses.of(passes);
    }

}
