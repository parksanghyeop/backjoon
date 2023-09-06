import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
//        parent = new int[N + 1];
//        Arrays.fill(parent, 0);
        parent = IntStream.rangeClosed(0, N).toArray();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }


        int[] intPath = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int count = 0;
        for (int i = 1; i < intPath.length; i++) {
            if (find(intPath[i]) != find(intPath[i - 1])) {
                count++;
            }
        }
        System.out.println(count);

    }

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
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}