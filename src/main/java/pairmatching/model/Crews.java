package pairmatching.model;

import java.util.ArrayList;
import java.util.List;

public class Crews {
    private List<Crew> backendCrews;
    private List<Crew> frontendCrews;

    public Crews(List<Crew> backendCrews, List<Crew> frontendCrews) {
        this.backendCrews = backendCrews;
        this.frontendCrews = frontendCrews;
    }

    public List<String> getCrewsAsString(String course) {
        List<String> crewList = new ArrayList<>();

        if (course.equals("백엔드")) {
            for (Crew crew : backendCrews) {
                crewList.add(crew.getName());
            }
            return crewList;
        }

        if (course.equals("프론트엔드")) {
            for (Crew crew : frontendCrews) {
                crewList.add(crew.getName());
            }
            return crewList;
        }

        throw new IllegalArgumentException();
    }
}
