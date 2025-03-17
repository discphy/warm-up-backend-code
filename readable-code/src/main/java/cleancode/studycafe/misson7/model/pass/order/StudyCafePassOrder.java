package cleancode.studycafe.misson7.model.pass.order;

import cleancode.studycafe.misson7.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.misson7.model.pass.seat.StudyCafeSeatPass;

import java.util.Optional;

public class StudyCafePassOrder {

    private final StudyCafeSeatPass seatPass;
    private final StudyCafeLockerPass lockerPass;

    private StudyCafePassOrder(StudyCafeSeatPass seatPass, StudyCafeLockerPass lockerPass) {
        this.seatPass = seatPass;
        this.lockerPass = lockerPass;
    }

    public static StudyCafePassOrder of(StudyCafeSeatPass seatPass, StudyCafeLockerPass lockerPass) {
        return new StudyCafePassOrder(seatPass, lockerPass);
    }

    public StudyCafeSeatPass getSeatPass() {
        return seatPass;
    }

    public Optional<StudyCafeLockerPass> getLockerPass() {
        return Optional.ofNullable(lockerPass);
    }

    public int getDiscountPrice() {
        return seatPass.getDiscountPrice();
    }

    public int getTotalPrice() {
        int lockerPassPrice = getLockerPassPrice();
        int seatPassPrice = seatPass.getPrice();

        int totalPrice = seatPassPrice + lockerPassPrice;
        return totalPrice - seatPass.getDiscountPrice();
    }

    private int getLockerPassPrice() {
        return Optional.ofNullable(lockerPass)
            .map(StudyCafeLockerPass::getPrice)
            .orElse(0);
    }
}
