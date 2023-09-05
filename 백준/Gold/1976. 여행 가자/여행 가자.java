import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 도시의 수 < 200
        int M = Integer.parseInt(br.readLine()); // 여행 계획에 속한 도시들의 수 < 1000

        parent = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
        }

        for (int i = 1; i < N + 1; i++) {

            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < N; j++) {
                if (line[j] == 1) {
                    union(i, j+1);
                }
            }
        }
        int[] plan = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int start = find(plan[0]);

        for (int now : plan) {
            if (start != find(now)) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");

    }
}