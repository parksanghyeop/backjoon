import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Position {
        int r;
        int c;
        int count;

        public Position(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }

    static char[][] map;
    static boolean[][] visited;
    static int n, m, result;

    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        result = 0;

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'L') { // 육지면 BFS
                    visited = new boolean[n][m];
                    BFS(new Position(i, j, 0));
                }
            }
        }
        System.out.println(result);
    }

    private static void BFS(Position position) {
        Queue<Position> q = new LinkedList<>();
        q.add(position);
        visited[position.r][position.c] = true;

        while (!q.isEmpty()) {
            Position current = q.poll();
            for (int k = 0; k < 4; k++) {
                int nr = current.r + dr[k];
                int nc = current.c + dc[k];

                if (nr < 0 || nc < 0 || nr >= n || nc >= m || visited[nr][nc] || map[nr][nc] == 'W')
                    continue;

                visited[nr][nc] = true;
                q.add(new Position(nr, nc, current.count + 1));
                result = Math.max(result, current.count + 1);
            }
        }
    }
}