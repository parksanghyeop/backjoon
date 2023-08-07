import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][][] dp = new int[100][100][100];

    // 뮤탈리스크 공격 경우의 수
    static int[][] attack = {{9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 9, 3}, {1, 3, 9}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Integer[] scv = new Integer[3];
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < 61; i++) {
            for (int j = 0; j < 61; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }
        // 정렬을 위해 레퍼런스 타입을 했으니 빈곳은 0으로 직접 초기화
        for (int i = n; i < 3; i++) {
            scv[i] = 0;
        }


        int result = search(scv.clone(), 0);
        System.out.println(result);
    }

    private static int search(Integer[] scv, int count) {
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (scv[i] != 0) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            return count;
        } else {
            // 내림차순 정렬
            Arrays.sort(scv, Collections.reverseOrder());

            // 이미 계산했던 곳이면 그냥 해당값 반환
            if (dp[scv[0]][scv[1]][scv[2]] != Integer.MAX_VALUE) {
                return dp[scv[0]][scv[1]][scv[2]];
            }

            // 뮤탈 공격
            for (int i = 0; i < 6; i++) {
                Integer[] temp = new Integer[3];
                temp[0] = Math.max(scv[0] - attack[i][0], 0);
                temp[1] = Math.max(scv[1] - attack[i][1], 0);
                temp[2] = Math.max(scv[2] - attack[i][2], 0);

                dp[scv[0]][scv[1]][scv[2]] = Math.min(dp[scv[0]][scv[1]][scv[2]], search(temp, count + 1));

            }

        }
        return dp[scv[0]][scv[1]][scv[2]];

    }

}