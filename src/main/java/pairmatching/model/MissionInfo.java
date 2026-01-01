package pairmatching.model;

import java.util.List;
import java.util.Objects;

public class MissionInfo {
    private Course course;
    private Level level;
    private String mission;

    public MissionInfo(Course course, Level level, String mission) {
        validate(course, level, mission);
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    private void validate(Course course, Level level, String mission) {
        List<String> validMissions = level.getMissions();

        if (!validMissions.contains(mission)) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 미션입니다.");
        }
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return level;
    }

    public String getMission() {
        return mission;
    }

    public boolean isSameLevel(MissionInfo other) {
        return this.course == other.course && this.level == other.level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MissionInfo that = (MissionInfo) o;

        return course == that.course &&
                level == that.level &&
                Objects.equals(mission, that.mission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, level, mission);
    }
}


/*
package pairmatching.model;

import java.util.List;

// 1. class 대신 record 키워드 사용 + 필드를 소괄호() 안에 선언
public record MissionInfo(Course course, Level level, String mission) {

    // 2. 컴팩트 생성자 (Compact Constructor)
    // 매개변수 선언을 생략하고 검증 로직만 작성합니다.
    public MissionInfo {
        List<String> validMissions = level.getMissions();

        if (!validMissions.contains(mission)) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 미션입니다.");
        }
    }

    // 3. 비즈니스 로직 메서드는 그대로 유지 가능
    public boolean isSameLevel(MissionInfo other) {
        return this.course == other.course && this.level == other.level;
    }

    // equals, hashCode, toString, Getter는
    // 작성하지 않아도 자동으로 생성됩니다.
}
 */