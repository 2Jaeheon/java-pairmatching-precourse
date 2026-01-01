package pairmatching.model;

import java.util.List;

public class Pair {
    private final List<String> crewNames;

    public Pair(List<String> crewNames) {
        this.crewNames = crewNames;
    }

    public boolean hasCommonCrew(Pair current) {
        for (String name : current.crewNames) {
            if (this.crewNames.contains(name)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getCrewNames() {
        return crewNames;
    }
}
