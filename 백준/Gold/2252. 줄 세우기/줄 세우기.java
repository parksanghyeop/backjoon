import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 위상 정렬에 사용할 진입차수 저장 배열
        int[] edgeCount = new int[N + 1];
        // 위상 정렬에 사용할 그래프 2차원 리스트로 구현
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<Integer>());
        }

        // 2차원 리스트의 인덱스가 학생번호
        // 주어진 키 비교정보에 따라 2차원 리스트 정보 초기화
        // 리스트 초기화 하면서 진입차수 배열 값 초기화
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            edgeCount[b]++;
        }

        // 위상정렬에 사용할 큐
        Queue<Integer> q = new LinkedList<>();

        // 진입 차수가 0인 값 큐에 넣기
        for (int i = 1; i < edgeCount.length; i++) {
            if (edgeCount[i] == 0) {
                q.offer(i);
            }
        }

        // 큐가 빌 때까지 반복
        while (!q.isEmpty()) {
            int studentNumber = q.poll();

            // 꺼낸 학생번호 출력값에 저장
            sb.append(studentNumber).append(" ");

            // 꺼낸 학생번호의 키와 비교한 정보 가져오기
            List<Integer> list = graph.get(studentNumber);

            // 키를 비교한 정보의 갯수 만큼 반복문 실행
            for (int i = 0; i < list.size(); i++) {
                // 해당 학생보다 뒤에 서야하는 학생의 정보 가져오기
                int temp = list.get(i);
                // 뒤에 서야하는 학생의 진입차수 감소
                edgeCount[temp]--;
                // 감소한 진입차수가 0이면 큐에 학생번호 넣기
                if (edgeCount[temp] == 0) {
                    q.offer(temp);
                }
            }
        }

        System.out.println(sb);
        
    }
}