package pairmatching.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {

    private static final String BACK_PATH = "src/main/resources/backend-crew.md";
    private static final String FRONT_PATH = "src/main/resources/frontend-crew.md";

    public Crews load() {
        // 백엔드 로드
        List<Crew> backends = loadBackends();

        // 프론트엔드 로드
        List<Crew> frontends = loadFrontends();
        // Crews 만들기

        return new Crews(backends, frontends);
    }

    private List<Crew> loadBackends() {
        List<Crew> backends = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(BACK_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                backends.add(new Crew(line, Course.BACKEND));
            }

            return backends;
        } catch (IOException e) {
            throw new IllegalArgumentException("[ERROR] 파일을 읽을 수 없습니다.");
        }
    }

    private List<Crew> loadFrontends() {
        List<Crew> frontends = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FRONT_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                frontends.add(new Crew(line, Course.FRONTEND));
            }

            return frontends;
        } catch (IOException e) {
            throw new IllegalArgumentException("[ERROR] 파일을 읽을 수 없습니다.");
        }
    }
}
