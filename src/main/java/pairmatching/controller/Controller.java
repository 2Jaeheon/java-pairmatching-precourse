package pairmatching.controller;

import static pairmatching.util.Parser.parseCommand;
import static pairmatching.util.Parser.parseMatching;

import java.util.List;
import pairmatching.model.Crews;
import pairmatching.model.FileLoader;
import pairmatching.model.MissionInfo;
import pairmatching.model.Pair;
import pairmatching.model.PairManager;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final PairManager pairManager;
    private final Crews crews;

    public Controller(InputView inputView, OutputView outputView, FileLoader fileLoader, PairManager pairManager) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.pairManager = pairManager;
        this.crews = fileLoader.load();
    }

    public void run() {
        while (true) {
            outputView.printMenu();
            String rawCmd = inputView.readCmd();

            // 종료
            if (rawCmd.equals("Q")) {
                return;
            }

            try {
                int cmd = parseCommand(rawCmd);
                processCmd(cmd);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void processCmd(int cmd) {
        if (cmd == 1) {
            matchPair();
        }

        if (cmd == 2) {
            searchPair();
        }

        if (cmd == 3) {
            clearPairs();
        }
    }

    private void clearPairs() {
        pairManager.clearPairs();
    }

    private void searchPair() {
        outputView.printMatchingMessage();
        MissionInfo missionInfo = parseMatching(inputView.readMatching());
        List<Pair> pairs = pairManager.search(missionInfo, crews);
        outputView.printPairs(pairs);
    }

    private void matchPair() {
        outputView.printMatchingMessage();
        MissionInfo missionInfo = parseMatching(inputView.readMatching());

        if (pairManager.isExist(missionInfo)) {
            if (isUserCancelRematch()) {
                return;
            }
            pairManager.removeHistory(missionInfo);
        }

        List<Pair> pairs = pairManager.match(missionInfo, crews);
        outputView.printPairs(pairs);
    }

    private boolean isUserCancelRematch() {
        outputView.printRematchQuestion();
        String answer = inputView.readCmd();

        if (answer.equals("아니오")) {
            return true;
        }
        if (answer.equals("네")) {
            return false;
        }
        throw new IllegalArgumentException("[ERROR] 네 또는 아니오만 입력 가능합니다.");
    }
}
