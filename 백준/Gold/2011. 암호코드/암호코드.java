import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] n = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        int length = n.length;

        int[] dp = new int[length + 1];

        if (n[0] == 0) {
            System.out.println(0);
        } else {
            dp[0] = 1;
            dp[1] = 1;

            for (int i = 2; i <= length; i++) {

                int prev1 = n[i-1];
                int prev2 = n[i-2];

                // 앞에 문자가 0이고 2칸 앞에가 1혹은 2이면
                if (prev1 == 0) {
                    if (prev2 == 1 || prev2 == 2)
                        dp[i] = dp[i - 2] % 1000000;
                    else // 00 이 만들어지므로 잘못된 코드 종료
                        break;
                } else {
                    // 앞 문자가 0이면 경우의수 변화 x
                    if (prev2 == 0)
                        dp[i] = dp[i - 1] % 1000000;
                    else {
                        int temp = prev2 * 10 + prev1;
                        if (temp >= 1 && temp <= 26) {
                            dp[i] = (dp[i - 2] + dp[i - 1]) % 1000000;
                        } else {
                            dp[i] = dp[i - 1] % 1000000;
                        }
                    }
                }
            }
            System.out.println(dp[length] % 1000000);
        }
    }
}