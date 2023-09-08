import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int arr[] = new int[S];
        for (int i = 0; i < S; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int l = 1;
        int r = arr[S - 1];
        long result = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            long tempSum = 0;
            int tempC = C;
            for (int i = 0; i < S; i++) {
                tempC -= arr[i] / mid;
                tempSum += arr[i] % mid;
            }
            if (tempC <= 0) {
                l = mid + 1;
                result = tempSum + (-tempC) * mid;
            } else r = mid - 1;
        }
        System.out.println(result);

    }
}