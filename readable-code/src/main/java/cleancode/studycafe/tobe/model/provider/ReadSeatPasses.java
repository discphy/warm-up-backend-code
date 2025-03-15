package cleancode.studycafe.tobe.model.provider;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.seat.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.seat.StudyCafeSeatPasses;

import java.util.List;

public class ReadSeatPasses {

    private static final String SPLITTER = ",";
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
        String[] values = line.split(SPLITTER);

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
