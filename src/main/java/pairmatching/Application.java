package pairmatching;

import pairmatching.controller.Controller;
import pairmatching.model.FileLoader;
import pairmatching.model.PairManager;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        FileLoader fileLoader = new FileLoader();
        PairManager pairManager = new PairManager();

        Controller controller = new Controller(inputView, outputView, fileLoader, pairManager);
        controller.run();
    }
}
