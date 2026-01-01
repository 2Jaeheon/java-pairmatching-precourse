package pairmatching.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PairManager {
    private Map<MissionInfo, List<Pair>> history = new LinkedHashMap<>();

    public List<Pair> match(MissionInfo missionInfo, Crews crews) {
        // 매칭을 이루는 과정
        for (int i = 0; i < 3; i++) {
            List<String> shuffledNames = Randoms.shuffle(crews.getCrewsByCourse(missionInfo.getCourse()));
            List<Pair> currentPairs = createPairs(shuffledNames);

            // 검증
            if (isValid(missionInfo, currentPairs)) {
                history.put(missionInfo, currentPairs);
                return currentPairs;
            }
        }

        throw new IllegalArgumentException("[ERROR] 3회 시도까지 매칭이 되지 않았거나 중복된 페어가 있습니다.");
    }

    private List<Pair> createPairs(List<String> names) {
        List<Pair> pairs = new ArrayList<>();
        int size = names.size();

        for (int i = 0; i < size; i += 2) {
            if (i == size - 3) {
                pairs.add(new Pair(Arrays.asList(names.get(i), names.get(i + 1), names.get(i + 2))));
                break;
            }

            pairs.add(new Pair(Arrays.asList(names.get(i), names.get(i + 1))));
        }

        return pairs;
    }

    // 같은 레벨에서 만났는지를 검증해야함
    private boolean isValid(MissionInfo missionInfo, List<Pair> currentPairs) {
        // 1. 전체 매칭 이력을 하나씩 꺼내어 검사
        for (Map.Entry<MissionInfo, List<Pair>> entry : history.entrySet()) {
            MissionInfo pastInfo = entry.getKey();

            // 2. 같은 코스면서 같은 레벨인 과거 기록만 필터링
            // 같은레벨이 아니면 패스
            if (pastInfo.isSameLevel(missionInfo)) {
                List<Pair> pastPairs = entry.getValue();

                // 3. 과거의 페어 목록과 현재 새로 만든 페어 목록을 이중 루프로 대조
                for (Pair current : currentPairs) {
                    for (Pair past : pastPairs) {
                        // 4. Pair 객체의 메서드를 활용해 단 한 명이라도 겹치는지 확인
                        if (past.hasCommonCrew(current)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public List<Pair> search(MissionInfo missionInfo, Crews crews) {
        if (!history.containsKey(missionInfo)) {
            throw new IllegalArgumentException("[ERROR] 매칭 이력이 없습니다.");
        }
        return history.get(missionInfo);
    }

    public void clearPairs() {
        this.history.clear();
    }

    public boolean isExist(MissionInfo missionInfo) {
        return history.containsKey(missionInfo);
    }

    public void removeHistory(MissionInfo missionInfo) {
        history.remove(missionInfo);
    }
}
