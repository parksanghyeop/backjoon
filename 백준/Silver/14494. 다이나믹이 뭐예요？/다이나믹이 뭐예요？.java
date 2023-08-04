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


        // 사이즈를 1 늘리고 시작하면
        // out of index를 고려하지 않고 연산에만 집중할 수 있다.
        int[][] arr = new int[n+1][m+1];

        // 시작지점 1로 초기화
        arr[0][0] = 1;


        for (int i=1;i<=n;i++) {
            for (int j=1;j<=m;j++) {
                // i,j 좌표의 위치까지 오는 경우의 수는
                // 상, 좌, 좌상 좌표의 경우의수를 모두 더한것과 같다.

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