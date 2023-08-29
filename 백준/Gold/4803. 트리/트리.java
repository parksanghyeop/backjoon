import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static ArrayList<Integer>[] tree;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            int result = 0;

            tree = new ArrayList[N];
            visited = new boolean[N];

            for (int i = 0; i < N; i++) {
                tree[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                tree[a].add(b);
                tree[b].add(a);
            }
            
            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    if (dfs(-1, i)) {
                        result++;
                    }
                }
            }
            sb.append("Case ").append(T).append(": ");
            if (result == 0) {
                sb.append("No trees.\n");
            } else if (result == 1) {
                sb.append("There is one tree.\n");
            } else {
                sb.append("A forest of ").append(result).append(" trees.\n");
            }
            T++;

        }
        System.out.println(sb);
    }

    private static boolean dfs(int root, int num) {
        for (int i : tree[num]) {
            if (i == root)
                continue;

            if (visited[i])
                return false;

            visited[i] = true;

            if (!dfs(num, i))
                return false;
        }
        return true;
    }
}