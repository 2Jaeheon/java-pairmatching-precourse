package pairmatching.model;

import java.util.List;

public class Pair {
    private final List<String> crewNames;

    public Pair(List<String> crewNames) {
        this.crewNames = crewNames;
    }

    public boolean isSamePair(Pair otherPair) {
        int matchCount = 0; // 교집합 개수
        for (String name : this.crewNames) {
            if (otherPair.crewNames.contains(name)) {
                matchCount++;
            }
        }

        return matchCount >= 2;
    }

    public List<String> getCrewNames() {
        return crewNames;
    }
}
