import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 입력받은 String 배열을 스트림을 활용해 정렬된 int 배열로 변환한다
        String[] strArr = br.readLine().split(" ");
        int[] budget = Arrays.stream(strArr).mapToInt(Integer::parseInt).sorted().toArray();

        int maxBudget = Integer.parseInt(br.readLine());

        int left = 0;
        int right = budget[budget.length - 1];


        while (left <= right) {
            // 중앙값을 구할 때 overflow를 방지한 공식
            int mid = left + (right - left) / 2;
            // 예산 요청에 따른 분배한 예산의 총합
            long total = 0;

            // 예산을 분배하며 예산 요청이 중앙값보다 크면 중앙값을 더하고
            // 중앙값보다 작으면 예산 요청값을 더한다.
            for (int i = 0; i < n; i++) {
                if (budget[i] > mid)
                    total += mid;
                else
                    total += budget[i];
            }

            // 예산이 남는다면 예산의 최소 범위를 증가시키고
            if (total <= maxBudget)
                left = mid + 1;
                // 예산이 부족하다면 예산의 최대 범위를 감소시킨다.
            else
                right = mid - 1;

        }
        System.out.println(right);

    }
}