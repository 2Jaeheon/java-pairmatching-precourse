package pairmatching.model;

import java.util.ArrayList;
import java.util.List;

public class Pair {
    private final List<String> crewNames;

    public Pair(List<String> names) {
        this.crewNames = new ArrayList<>(names);
    }

    public boolean hasCommonCrew(Pair other) {
        for (String name : other.crewNames) {
            if (this.crewNames.contains(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.join(" : ", crewNames);
    }
}