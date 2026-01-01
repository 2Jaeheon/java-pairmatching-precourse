package pairmatching.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {
    private static final String BACKEND_PATH = "src/main/resources/backend-crew.md";
    private static final String FRONTEND_PATH = "src/main/resources/frontend-crew.md";

    public Crews load() {
        // 백엔드 가져오기
        List<Crew> backendCrews = loadBackendDevelopers();
        // 프론트 가져오기
        List<Crew> frontendCrews = loadFrontendDevelopers();
        // Crews 만들기
        return new Crews(backendCrews, frontendCrews);
    }

    private List<Crew> loadBackendDevelopers() {
        List<Crew> backendCrews = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(BACKEND_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                backendCrews.add(new Crew(line, Course.BACKEND));
            }

            return backendCrews;
        } catch (IOException e) {
            throw new IllegalArgumentException("[ERROR] 백엔드 크루 파일을 찾을 수 없습니다.");
        }
    }

    private List<Crew> loadFrontendDevelopers() {
        List<Crew> frontendCrews = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FRONTEND_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                frontendCrews.add(new Crew(line, Course.FRONTEND));
            }

            return frontendCrews;
        } catch (IOException e) {
            throw new IllegalArgumentException("[ERROR] 백엔드 크루 파일을 찾을 수 없습니다.");
        }
    }
}
