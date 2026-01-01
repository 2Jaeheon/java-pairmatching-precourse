package pairmatching.model;

import java.util.Objects;

public class MissionInfo {
    private final Course course;
    private final Level level;
    private final String mission;

    public MissionInfo(String courseName, String levelName, String missionName) {
        this.course = Course.findCourseBy(courseName.trim());
        this.level = Level.findLevel(levelName.trim());
        this.mission = missionName.trim();
        validateMission(this.level, this.mission); // 검증 추가
    }

    private void validateMission(Level level, String mission) {
        if (!level.getMissions().contains(mission)) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 미션입니다.");
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MissionInfo)) {
            return false;
        }
        MissionInfo that = (MissionInfo) o;
        return course == that.course && level == that.level && mission.equals(that.mission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, level, mission);
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return level;
    }
}