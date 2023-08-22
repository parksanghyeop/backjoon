import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        if (n == 1 && m == 1) {
            System.out.println(1);
            return;
        }

        int[][] dp = new int[n + 1][m + 1];
        int result = 0;

        for (int i = 1; i <= n; i++) {
            String[] input = br.readLine().split("");

            for (int j = 1; j <= m; j++) {
                int temp = Integer.parseInt(input[j - 1]);

                if (i == 1 && j == 1) {
                    dp[i][j] = temp;
                } else { // (1, 2) 부터는 왼쪽, 위쪽, 왼쪽 위 중 가장 작은 값에 1을 더한 값을 dp[i][j]에 저장.
                    if (temp == 1) {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                        result = Math.max(result, dp[i][j]);
                    }
                }
            }
        }

        System.out.println(result*result);
    }
}