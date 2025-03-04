package study.misson4.domain;

public class Member {

    private final Long id;
    private final String name;
    private final String email;
    private final String phoneNumber;

    private Member(Long id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public boolean hasNotInfo() {
        return name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty();
    }
}
