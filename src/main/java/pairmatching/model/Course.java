package pairmatching.model;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    public static Course findCourseBy(String courseName) {
        for (Course course : values()) {
            if (course.name.equals(courseName)) {
                return course;
            }
        }

        throw new IllegalArgumentException("[ERROR] 코스를 찾을 수 없습니다.");
    }

    public String getName() {
        return name;
    }
}
