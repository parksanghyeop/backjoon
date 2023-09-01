import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = br.readLine();

            if (line.equals("end"))
                break;

            map = new char[3][3];

            // 틱택토 2차원으로 변환
            for (int i = 0; i < 9; i++) {
                map[i / 3][i % 3] = line.charAt(i);
            }

            if (check(map)) {
                System.out.println("valid");
            } else {
                System.out.println("invalid");
            }
        }
    }

    private static boolean check(char[][] map) {
        int oCount = 0;
        int xCount = 0;
        int empty = 0;
        for (int i = 0; i < 9; i++) {
            if (map[i / 3][i % 3] == 'X') {
                xCount++;
            } else if (map[i / 3][i % 3] == 'O') {
                oCount++;
            } else {
                empty++;
            }
        }

        if (xCount + oCount == 9) {

            // x가 먼저 시작하므로 꽉차있으면 항상 1개더 많아야함
            if (xCount != oCount + 1 || isValid(map, 'O')) {
                return false;
            }
            return true;
        } else {
            // O로 끝나야 하는 경우
            if (xCount == oCount) {
                boolean isO = isValid(map, 'O');
                boolean isX = isValid(map, 'X');

                // O가 이기면 true 나머진 false
                if (isO && !isX) {
                    return true;
                } else {
                    return false;
                }
            }

            // X로 끝나야 하는 경우
            else if (xCount == oCount + 1) {
                boolean isO = isValid(map, 'O');
                boolean isX = isValid(map, 'X');

                // X가 이기면 true 나머진 false
                if (!isO && isX) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        return false;

    }

    private static boolean isValid(char[][] map, char c) {
        // 가로
        for (int i = 0; i < 3; i++) {
            if (map[i][0] == c && map[i][1] == c && map[i][2] == c)
                return true;
        }

        // 세로
        for (int i = 0; i < 3; i++) {
            if (map[0][i] == c && map[1][i] == c && map[2][i] == c)
                return true;
        }

        // 대각선
        if (map[0][0] == c && map[1][1] == c && map[2][2] == c)
            return true;

        if (map[0][2] == c && map[1][1] == c && map[2][0] == c)
            return true;

        return false;
    }


}