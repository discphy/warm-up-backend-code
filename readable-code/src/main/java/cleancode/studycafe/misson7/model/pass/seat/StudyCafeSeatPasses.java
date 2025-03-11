package cleancode.studycafe.misson7.model.pass.seat;

import cleancode.studycafe.misson7.model.pass.StudyCafePassType;

import java.util.List;

public class StudyCafeSeatPasses {

    private final List<StudyCafeSeatPass> passes;

    private StudyCafeSeatPasses(List<StudyCafeSeatPass> passes) {
        this.passes = passes;
    }

    public static StudyCafeSeatPasses of(List<StudyCafeSeatPass> passes) {
        return new StudyCafeSeatPasses(passes);
    }

    public StudyCafeSeatPasses findCandidatesBy(StudyCafePassType passType) {
        List<StudyCafeSeatPass> passCandidates = passes.stream()
            .filter(passType::isSamePassType)
            .toList();

        return of(passCandidates);
    }

    public int size() {
        return passes.size();
    }

    public StudyCafeSeatPass findBy(int index) {
        return passes.get(index);
    }
}
