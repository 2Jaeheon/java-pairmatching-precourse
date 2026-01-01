package pairmatching.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Level {
    LEVEL1("레벨1", Arrays.asList("자동차경주", "로또", "숫자야구게임")),
    LEVEL2("레벨2", Arrays.asList("장바구니", "결제", "지하철노선도")),
    LEVEL3("레벨3", Collections.emptyList()),
    LEVEL4("레벨4", Arrays.asList("성능개선", "배포")),
    LEVEL5("레벨5", Collections.emptyList());

    private String name;
    private List<String> missions;

    Level(String name, List<String> missions) {
        this.name = name;
        this.missions = missions;
    }

    public static Level findLevel(String name) {
        for (Level level : values()) {
            if (level.name.equals(name)) {
                return level;
            }
        }

        throw new IllegalArgumentException("[ERROR] 해당 레벨은 존재하지 않습니다.");
    }

    public List<String> getMissions() {
        return missions;
    }

    public String getName() {
        return name;
    }
}