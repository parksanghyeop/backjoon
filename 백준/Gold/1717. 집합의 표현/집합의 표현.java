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
        if (a != b) {
            parent[b] = a;
        }
    }

    private static int find(int a) {
        if(parent[a] == a)
            return a;
        else return parent[a] = find(parent[a]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            parent[i] = i;
        }

        while (m-- > 0) {
            String[] strLine = br.readLine().split(" ");
            int[] line = Arrays.stream(strLine).mapToInt(Integer::parseInt).toArray();

            int command = line[0];
            int a = line[1];
            int b = line[2];

            switch (command) {
                case 0:
                    union(a, b);
                    break;
                case 1:
                    if (find(a) == find(b)) {
                        System.out.println("YES");
                    } else {
                        System.out.println("NO");
                    }
                    break;
            }
        }

    }
}