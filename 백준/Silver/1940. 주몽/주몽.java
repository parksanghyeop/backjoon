import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 재료의 갯수
        int m = Integer.parseInt(br.readLine()); // 갑옷을 만드는데 필요한 수

        String[] strArr = br.readLine().split(" ");
        int armor[] = Arrays.stream(strArr).mapToInt(Integer::parseInt).sorted().toArray();

        int count = 0;

        for (int start = 0; start < n; start++) {
            int sum = 0;
            int end = start + 1;

            while (end < n) {
                sum = armor[start] + armor[end++];
                if (sum == m) {
                    count++;
                    break;
                }
            }
        }

        System.out.println(count);

    }
}