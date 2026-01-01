package pairmatching.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Matcher {
    private final Map<MissionInfo, List<Pair>> history = new HashMap<>();

    public List<Pair> match(List<String> query, Crews crews) {
        MissionInfo missionInfo = new MissionInfo(query.get(0), query.get(1), query.get(2));

        for (int i = 0; i < 3; i++) {
            List<String> shuffledNames = Randoms.shuffle(crews.getCrewsAsString(missionInfo.getCourse().getName()));
            List<Pair> currentPairs = new ArrayList<>();

            // 페어 생성 로직
            for (int j = 0; j < shuffledNames.size(); j += 2) {
                if (j == shuffledNames.size() - 3) {
                    currentPairs.add(new Pair(
                            Arrays.asList(shuffledNames.get(j), shuffledNames.get(j + 1), shuffledNames.get(j + 2))));
                    break;
                }
                currentPairs.add(new Pair(Arrays.asList(shuffledNames.get(j), shuffledNames.get(j + 1))));
            }

            // 중복 검증 로직
            boolean isDuplicate = false;
            for (Map.Entry<MissionInfo, List<Pair>> entry : history.entrySet()) {

                if (entry.getKey().getCourse() == missionInfo.getCourse()
                        && entry.getKey().getLevel() == missionInfo.getLevel()) {

                    if (hasCommonPair(entry.getValue(), currentPairs)) {
                        isDuplicate = true;
                    }
                }
            }

            if (!isDuplicate) {
                history.put(missionInfo, currentPairs);
                return currentPairs;
            }
        }
        throw new IllegalArgumentException("[ERROR] 3회 시도까지 매칭이 되지 않았거나 중복된 페어가 있습니다.");
    }

    private boolean hasCommonPair(List<Pair> pastPairs, List<Pair> currentPairs) {
        for (Pair current : currentPairs) {
            for (Pair past : pastPairs) {
                if (past.hasCommonCrew(current)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Pair> search(List<String> query) {
        MissionInfo info = new MissionInfo(query.get(0), query.get(1), query.get(2));
        if (!history.containsKey(info)) {
            throw new IllegalArgumentException("[ERROR] 매칭 이력이 없습니다.");
        }
        return history.get(info);
    }

    public boolean isExist(List<String> query) {
        return history.containsKey(new MissionInfo(query.get(0), query.get(1), query.get(2)));
    }

    public void reset() {
        history.clear();
    }
}