package pairmatching.model;

import java.util.ArrayList;
import java.util.List;

public class Crews {
    private List<Crew> backends;
    private List<Crew> frontends;

    public Crews(List<Crew> backends, List<Crew> frontends) {
        this.backends = backends;
        this.frontends = frontends;
    }

    public List<String> getCrewsByCourse(Course course) {
        if (course == Course.BACKEND) {
            return getNames(backends);
        }

        if (course == Course.FRONTEND) {
            return getNames(frontends);
        }

        throw new IllegalArgumentException("[ERROR] 유효하지 않은 코스입니다.");
    }

    private List<String> getNames(List<Crew> crewList) {
        List<String> names = new ArrayList<>();
        for (Crew crew : crewList) {
            names.add(crew.getName());
        }
        return names;
    }
}
