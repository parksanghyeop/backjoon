import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static int[][] map;
    private static boolean[][] visit;

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(reader.readLine());

        map = new int[n + 1][n + 1];
        visit = new boolean[n + 1][n + 1];

        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(result);

    }

    private static void dfs(int count, int sum) {

        if (count == 3) {
            //꽃 3개

            result = Math.min(result, sum);

        } else {

            for (int i = 2; i < n; i++) {
                for (int j = 2; j < n; j++) {
                    if (!visit[i][j] && check(i, j)) {
                        visit[i][j] = true;
                        int hap = sum(i, j);//꽃 합

                        dfs(count + 1, sum + hap);

                        visitClear(i, j);
                        visit[i][j] = false;
                    }
                }
            }

        }

    }//bfs

    private static boolean check(int x, int y) {

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (visit[nx][ny]) {
                return false;
            }
        }

        return true;

    }//check

    private static void visitClear(int x, int y) {

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            visit[nx][ny] = false;
        }

    }//visitClear

    private static int sum(int x, int y) {

        int hap = map[x][y];

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            visit[nx][ny] = true;
            hap += map[nx][ny];
        }

        return hap;

    }//sum
}