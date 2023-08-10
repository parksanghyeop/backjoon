import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int n,m;
    private static int time = 0, lastCheese = 0;
    private static int arr[][];
    private static boolean visited[][];

    private static class Position {
        int r;
        int c;

        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int[] dr = {0, 0, 1, -1};
    private static int[] dc = {1, -1, 0, 0};


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] strArr = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(strArr[j]);
            }
        }

        loop1: for (int i = 0; i < n; i++) {
            loop2: for (int j = 0; j < m; j++) {
                //치즈가 남아있다면 BFS 돌리면서 남은 치즈 찾기
                if (arr[i][j] == 1 ) {
                    lastCheese=0;
                    visited = new boolean[n][m];
                    visited[0][0] = true;
                    BFS(0, 0);
                    time+=1;
//                    for(int[] line : arr)
//                        System.out.println(Arrays.toString(line));
//                    System.out.println("===========================");
                }

            }
        }

        System.out.println(time);
        System.out.println(lastCheese);


    }

    private static void BFS(int i, int j) {
        Queue<Position> q = new LinkedList<>();
        q.add(new Position(i, j));
        while (!q.isEmpty()) {
            Position current = q.poll();

            for (int k = 0; k < 4; k++) {
                int nr = current.r + dr[k];
                int nc = current.c + dc[k];

                // 탐색 불가지역 continue
                if (nr < 0 || nc < 0 || nr >= n || nc >= m || visited[nr][nc])
                    continue;

                // 다음 탐색지역이 치즈면 치즈 녹이고 방문처리 큐에는 넣지 않는다
                if (arr[nr][nc] == 1) {
                    arr[nr][nc] = 0;
                    visited[nr][nc] = true;
                    lastCheese+=1;

                    // 다음 탐색지역이 비었으면 큐에 추가
                } else if (arr[nr][nc] == 0) {
                    q.add(new Position(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
    }
}