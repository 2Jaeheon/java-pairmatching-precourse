package pairmatching.controller;

import java.util.List;
import pairmatching.model.Crews;
import pairmatching.model.FileLoader;
import pairmatching.model.Matcher;
import pairmatching.model.Pair;
import pairmatching.parser.Parser;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final Matcher matcher;
    private final Crews crews;

    public Controller(InputView inputView, OutputView outputView, FileLoader fileLoader, Matcher matcher) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.matcher = matcher;
        this.crews = fileLoader.load();
    }

    public void run() {
        while (true) {
            try {
                outputView.printMenu();
                String cmd = inputView.readCmd();
                if (cmd.equals("Q")) {
                    break;
                }
                processCommand(cmd);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private void processCommand(String cmd) {
        int cmdNumber = Parser.parseCmd(cmd);
        if (cmdNumber == 1) {
            matchPair();
        }
        if (cmdNumber == 2) {
            searchPair();
        }
        if (cmdNumber == 3) {
            resetPair();
        }
    }

    private void matchPair() {
        outputView.printCourseAndMissions();
        List<String> query = Parser.parse(inputView.readCourseAndLevelAndMission());

        if (matcher.isExist(query)) {
            // 이력이 있다면 사용자에게 재매칭 의사를 물어봄
            if (isUserCancelRematch()) {
                return; // 사용자가 아니오 택하면 종료
            }
        }

        List<Pair> result = matcher.match(query, crews);
        outputView.printMatchingResult(result);
    }

    private boolean isUserCancelRematch() {
        String answer = inputView.readRematch();
        if (answer.equals("네")) {
            return false;
        }
        if (answer.equals("아니오")) {
            return true;
        }

        throw new IllegalArgumentException("[ERROR] 네 또는 아니오만 입력 가능합니다.");
    }

    private void searchPair() {
        outputView.printCourseAndMissions();
        List<String> query = Parser.parse(inputView.readCourseAndLevelAndMission());
        List<Pair> result = matcher.search(query);
        outputView.printMatchingResult(result);
    }

    private void resetPair() {
        matcher.reset();
        outputView.printResetMessage();
    }
}