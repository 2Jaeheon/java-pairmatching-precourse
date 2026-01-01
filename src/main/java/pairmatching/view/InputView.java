package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String readCmd() {
        return Console.readLine();
    }

    public String readCourseAndLevelAndMission() {
        return Console.readLine();
    }

    public String readRematch() {
        System.out.println("\n매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        System.out.println("네 | 아니오");
        return Console.readLine();
    }
}