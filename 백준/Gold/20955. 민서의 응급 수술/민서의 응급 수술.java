import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        Arrays.fill(parent, -1);
//        parent = IntStream.rangeClosed(0,N).toArray();

        int count = 0;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (!union(u, v)) { // 이미 두개가 같은 집합이라면 사이클이 존재한는 뜻이므로 사이클을 제거하는 연산 +1
                count++;
            }
        }
        for (int i = 1; i < N + 1; i++) {
            if (parent[i] < 0) {
                count++;
            }
        }
        System.out.println(--count);
    }

    private static boolean union(int a, int b) {
//        System.out.println("=====================");
//        System.out.println(a + " " + b);
        a = find(a);
        b = find(b);
//        System.out.println("findA: " + a + " findB: " + b);
        if (a == b) return false;
        int h = parent[a] < parent[b] ? a : b;
        int l = parent[a] < parent[b] ? b : a;

        parent[h] += parent[l]; // 더 많은 자식을 가진 루트로 만들어줌
        parent[l] = h;
//        System.out.println("h : " + h + " | l : " + l);
//        System.out.println(Arrays.toString(parent));
        return true;
    }

    private static int find(int a) {
        if (parent[a] < 0) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}