package pairmatching.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pairmatching.model.Course;
import pairmatching.model.Level;
import pairmatching.model.MissionInfo;

public class Parser {
    public static int parseCommand(String raw) {
        try {
            List<String> checkingList = new ArrayList<>(Arrays.asList("1", "2", "Q"));
            if (!checkingList.contains(raw)) {
                throw new IllegalArgumentException("[ERROR] 1, 2, 3, Q 중 하나로 입력해주세요.");
            }

            return Integer.parseInt(raw);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 입력값을 변환할 수 없습니다.");
        }
    }

    public static MissionInfo parseMatching(String raw) {
        String[] split = raw.split(",");

        if (split.length != 3) {
            throw new IllegalArgumentException("[ERROR] 과정, 레벨, 미션 형식으로 입력해주세요.");
        }

        List<String> trimmedResult = new ArrayList<>();
        for (String s : split) {
            trimmedResult.add(s.trim());
        }

        Course course = Course.findByName(trimmedResult.get(0));
        Level level = Level.findByName(trimmedResult.get(1));
        String mission = trimmedResult.get(2);

        return new MissionInfo(course, level, mission);
    }
}
