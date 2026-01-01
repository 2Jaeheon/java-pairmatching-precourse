package pairmatching.view;

import java.util.ArrayList;
import java.util.List;
import pairmatching.model.Course;
import pairmatching.model.Level;
import pairmatching.model.Pair;

public class OutputView {
    public void printMenu() {
        System.out.println("기능을 선택하세요.");
        System.out.println("1. 페어 매칭");
        System.out.println("2. 페어 조회");
        System.out.println("3. 페어 초기화");
        System.out.println("Q. 종료");
    }

    public void printMatchingMessage() {
        System.out.println("#############################################");
        String message = "과정: ";
        List<String> list = new ArrayList<>();
        for (Course course : Course.values()) {
            list.add(course.getName());
        }
        message += String.join(" | ", list);
        System.out.println(message);

        System.out.println("미션: ");

        List<String> levels = new ArrayList<>();
        for (Level level : Level.values()) {
            StringBuilder missions = new StringBuilder("  - ");
            missions.append(level.getName()).append(": ");
            List<String> missionList = level.getMissions();
            missions.append(String.join(" | ", missionList));
            System.out.println(missions);
        }

        System.out.println("#############################################");
        System.out.println("과정, 레벨, 미션을 선택하세요.");
        System.out.println("ex) 백엔드, 레벨1, 자동차경주");
    }

    public void printPairs(List<Pair> pairs) {
        System.out.println("페어 매칭 결과입니다.");

        for (Pair pair : pairs) {
            List<String> crewNames = pair.getCrewNames();
            String crews = String.join(" : ", crewNames);
            System.out.println(crews);
        }
    }

    public void printRematchQuestion() {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        System.out.println("네 | 아니오");
    }
}
