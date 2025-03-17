package cleancode.studycafe.misson7.model.pass;

import cleancode.studycafe.misson7.exception.AppException;
import cleancode.studycafe.misson7.format.PassTypeFormatter;
import cleancode.studycafe.misson7.model.pass.seat.StudyCafeSeatPass;
import cleancode.studycafe.misson7.select.PassTypeSelectable;

import java.util.Arrays;

public enum StudyCafePassType implements PassTypeSelectable, PassTypeFormatter {

    HOURLY("시간 단위 이용권") {
        @Override
        public boolean selected(String userInput) {
            return "1".equals(userInput);
        }

        @Override
        public String format(StudyCafePass pass) {
            return String.format("%s시간권 - %d원", pass.getDuration(), pass.getPrice());
        }
    },
    WEEKLY("주 단위 이용권") {
        @Override
        public boolean selected(String userInput) {
            return "2".equals(userInput);
        }

        @Override
        public String format(StudyCafePass pass) {
            return String.format("%s주권 - %d원", pass.getDuration(), pass.getPrice());
        }
    },
    FIXED("1인 고정석") {
        @Override
        public boolean selected(String userInput) {
            return "3".equals(userInput);
        }

        @Override
        public String format(StudyCafePass pass) {
            return String.format("%s주권 - %d원", pass.getDuration(), pass.getPrice());
        }
    };

    private final String description;

    StudyCafePassType(String description) {
        this.description = description;
    }

    public static StudyCafePassType findBy(String userInput) {
        return Arrays.stream(values())
            .filter(passType -> passType.selected(userInput))
            .findFirst()
            .orElseThrow(() -> new AppException("잘못된 입력입니다."));
    }

    public boolean isSamePassType(StudyCafeSeatPass pass) {
        return this.equals(pass.getPassType());
    }
}
