package pairmatching.view;

import java.util.List;
import pairmatching.model.Level;
import pairmatching.model.Pair;

public class OutputView {
    public void printMenu() {
        System.out.println("\n기능을 선택하세요.\n1. 페어 매칭\n2. 페어 조회\n3. 페어 초기화\nQ. 종료");
    }

    public void printCourseAndMissions() {
        System.out.println("\n#############################################");
        System.out.println("과정: 백엔드 | 프론트엔드");
        System.out.println("미션: ");
        for (Level level : Level.values()) {
            System.out.println("  - " + level.getName() + ": " + String.join(" | ", level.getMissions()));
        }
        System.out.println("############################################");
        System.out.println("과정, 레벨, 미션을 선택하세요.\nex) 백엔드, 레벨1, 자동차경주");
    }

    public void printMatchingResult(List<Pair> pairs) {
        System.out.println("\n페어 매칭 결과입니다.");
        for (Pair pair : pairs) {
            System.out.println(pair.toString());
        }
    }

    public void printResetMessage() {
        System.out.println("\n초기화 되었습니다.");
    }

    public void printError(String message) {
        System.out.println(message);
    }
}