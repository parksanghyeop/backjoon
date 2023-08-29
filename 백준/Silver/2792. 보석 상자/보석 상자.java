import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int arr[] = new int[M];
        int result = 0;
        int left = 1;
        int right = 0;
        int mid = 0;
        int sum;


        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            right = Math.max(right, arr[i]);
        }



        while (left <= right) {

            mid = (left+right) / 2;
            sum = 0;

            // 질투심이 mid가 되도록 보석 나누는 과정
            for(int i=0;i<M;i++) {
                sum += arr[i] / mid;
                if(arr[i] % mid != 0) {
                    sum++;
                }
            }

            // 보석을 나눠줄 수 없는 경우라면
            if (sum > N) {
                left = mid +1;
            }

            // 보석을 나눶루 수 있다면
            else {
                right = mid -1;
                result = mid;
            }
        }

        System.out.println(result);
    }
}