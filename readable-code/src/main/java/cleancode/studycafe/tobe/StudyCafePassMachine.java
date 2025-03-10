package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.*;

import java.util.Optional;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            StudyCafePass selectedPass = selectedPass();
            Optional<StudyCafeLockerPass> selectedLockerPass = selectedLockerPass(selectedPass);

            selectedLockerPass.ifPresentOrElse(
                lockerPass -> outputHandler.showPassOrderSummary(selectedPass, lockerPass),
                () -> outputHandler.showPassOrderSummary(selectedPass)
            );
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePass selectedPass() {
        StudyCafePassType passType = selectedPassType();
        StudyCafePasses passCandidates = findPassCandidates(passType);

        outputHandler.showPassListForSelection(passCandidates);
        return inputHandler.getSelectPass(passCandidates);
    }

    private StudyCafePassType selectedPassType() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    private StudyCafePasses findPassCandidates(StudyCafePassType passType) {
        StudyCafePasses allPass = studyCafeFileHandler.readStudyCafePasses();
        return allPass.findCandidatesBy(passType);
    }

    private Optional<StudyCafeLockerPass> selectedLockerPass(StudyCafePass pass) {
        if (pass.cannotSelectLockerPass()) {
            return Optional.empty();
        }

        Optional<StudyCafeLockerPass> lockerPassCandidate = findLockerPassCandidate(pass);
        return lockerPassCandidate
            .filter(this::isSelectedLockerPass);
    }

    private Optional<StudyCafeLockerPass> findLockerPassCandidate(StudyCafePass pass) {
        StudyCafeLockerPasses lockerPasses = studyCafeFileHandler.readLockerPasses();
        return lockerPasses.findCandidateBy(pass);

    }

    private boolean isSelectedLockerPass(StudyCafeLockerPass lockerPass) {
        outputHandler.askLockerPass(lockerPass);
        return inputHandler.getLockerSelection();
    }
}
