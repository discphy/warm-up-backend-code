package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.config.StudyCafeConfig;
import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.exception.ProvideException;
import cleancode.studycafe.tobe.io.handler.InputHandler;
import cleancode.studycafe.tobe.io.handler.OutputHandler;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPasses;
import cleancode.studycafe.tobe.model.pass.order.StudyCafePassOrder;
import cleancode.studycafe.tobe.model.pass.seat.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.seat.StudyCafeSeatPasses;
import cleancode.studycafe.tobe.provider.LockerPassProvider;
import cleancode.studycafe.tobe.provider.SeatPassProvider;

import java.util.Optional;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final SeatPassProvider seatPassProvider;
    private final LockerPassProvider lockerPassProvider;

    public StudyCafePassMachine(StudyCafeConfig config) {
        this.inputHandler = config.getInputHandler();
        this.outputHandler = config.getOutputHandler();
        this.seatPassProvider = config.getSeatPassProvider();
        this.lockerPassProvider = config.getLockerPassProvider();
    }

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            StudyCafeSeatPass selectedSeatPass = selectedSeatPass();
            Optional<StudyCafeLockerPass> selectedLockerPass = selectedLockerPass(selectedSeatPass);

            StudyCafePassOrder order = StudyCafePassOrder.of(
                selectedSeatPass,
                selectedLockerPass.orElse(null)
            );
            outputHandler.showPassOrderSummary(order);
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (ProvideException e) {
            outputHandler.showSimpleMessage("이용권을 제공받을 수 없습니다.");
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafeSeatPass selectedSeatPass() {
        StudyCafePassType passType = selectedPassType();
        StudyCafeSeatPasses seatPassCandidates = findSeatPassCandidates(passType);

        outputHandler.showSeatPassListForSelection(seatPassCandidates);
        return inputHandler.getSelectSeatPass(seatPassCandidates);
    }

    private StudyCafePassType selectedPassType() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    private StudyCafeSeatPasses findSeatPassCandidates(StudyCafePassType passType) {
        StudyCafeSeatPasses allPass = seatPassProvider.provide();
        return allPass.findCandidatesBy(passType);
    }

    private Optional<StudyCafeLockerPass> selectedLockerPass(StudyCafeSeatPass seatPass) {
        if (seatPass.cannotSelectLockerPass()) {
            return Optional.empty();
        }

        Optional<StudyCafeLockerPass> lockerPassCandidate = findLockerPassCandidate(seatPass);
        return lockerPassCandidate
            .filter(this::isSelectedLockerPass);
    }

    private Optional<StudyCafeLockerPass> findLockerPassCandidate(StudyCafeSeatPass seatPass) {
        StudyCafeLockerPasses lockerPasses = lockerPassProvider.provide();
        return lockerPasses.findCandidateBy(seatPass);
    }

    private boolean isSelectedLockerPass(StudyCafeLockerPass lockerPass) {
        outputHandler.askLockerPass(lockerPass);
        return inputHandler.getLockerSelection();
    }
}
