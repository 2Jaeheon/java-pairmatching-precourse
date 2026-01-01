package pairmatching.parser;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static final String CMD_ERROR = "[ERROR] 1, 2, 3, Q 중 하나를 입력해주세요.";
    private static final String FORMAT_ERROR = "[ERROR] 과정, 레벨, 미션 형식으로 입력해주세요.";

    public static int parseCmd(String raw) {
        try {
            int parsedCmd = Integer.parseInt(raw);
            if (parsedCmd < 1 || parsedCmd > 3) {
                throw new IllegalArgumentException(CMD_ERROR);
            }
            return parsedCmd;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(CMD_ERROR);
        }
    }

    public static List<String> parse(String raw) {
        String[] split = raw.split(",");

        if (split.length != 3) {
            throw new IllegalArgumentException(FORMAT_ERROR);
        }

        List<String> trimmedResult = new ArrayList<>();
        for (String s : split) {
            trimmedResult.add(s.trim());
        }

        return trimmedResult;
    }
}