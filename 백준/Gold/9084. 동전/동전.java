import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int coinCount = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] coin = new int[coinCount];
            for (int i = 0; i < coinCount; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }

            int money = Integer.parseInt(br.readLine());

            int[] dp = new int[money + 1];

            dp[0] = 1;

            for (int i = 0; i < coinCount; i++) {
                for (int j = coin[i]; j <= money; j++) {
                    dp[j] += dp[j - coin[i]];
                }
            }

            System.out.println(dp[money]);

        }
    }
}