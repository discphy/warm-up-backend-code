package study.misson4.domain;

import java.util.ArrayList;
import java.util.List;

public class Items {

    private final List<Item> list = new ArrayList<>();

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
