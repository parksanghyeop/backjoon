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
        final int MOD = 1000000007;

        int[][] arr = new int[n+1][m+1];

        arr[0][0] = 1;

        for (int i=1;i<=n;i++) {
            for (int j=1;j<=m;j++) {
                arr[i][j] = arr[i-1][j-1];
                arr[i][j] += arr[i][j-1];
                arr[i][j] %= MOD;

                arr[i][j] += arr[i-1][j];
                arr[i][j] %= MOD;
            }
        }

        System.out.println(arr[n][m]);
    }
}