import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class CodeTree20230809 {
    // 미로 타워 디펜스 Gold_1

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    private static int arr[][]; // 격자
    private static int temp[][]; // 임시 격자
    private static int n, m, score = 0;     // 격자 크기, 총 라운드 수
    private static ArrayList<Point> spiralPoints = new ArrayList<>();


    private static void searchSpiral() {
        int[] dr = {0, 1, 0, -1};
        int[] dc = {-1, 0, 1, 0};

        int currentR = n / 2;
        int currentC = n / 2;
        int moveDirection = 0;
        int moveNum = 1;

        while (currentR > 0 || currentC > 0) {
            for (int i = 0; i < moveNum; i++) {
                currentR += dr[moveDirection];
                currentC += dc[moveDirection];
                spiralPoints.add(new Point(currentR, currentC));

                if (currentC == 0 && currentC == 0)
                    break;
            }

            moveDirection = (moveDirection + 1) % 4;

            if (moveDirection == 0 || moveDirection == 2) {
                moveNum++;
            }
        }
    }

    private static void simulate(int d, int p) {
        attack(d, p);

        bombAndFill();

        lookAndSay();
    }

    private static void lookAndSay() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                temp[i][j] =0;
            }
        }

        int tempIndex = 0;
        int currentIndex = 0;
        int listSize = spiralPoints.size();

        while (currentIndex < listSize) {
            int endindex = getEndIndex(currentIndex);

            // 연속하여 나온 숫자의 개수와 숫자 종류 값을 계산합니다.
            int contiguousCnt = endindex - currentIndex + 1;
            int currNum = getNumCurrentIndex(currentIndex);

            // 맨 끝에 도달하게 되면, 더이상 진행하지 않습니다.
            if(currNum == 0)
                break;

            // temp에 (개수, 종류) 순서대로 기록해줍니다.
            // 만약 격자를 벗어나면 종료합니다.
            if(tempIndex >= listSize)
                break;

            int tempR = spiralPoints.get(tempIndex).r;
            int tempC = spiralPoints.get(tempIndex).c;
            temp[tempR][tempC] = contiguousCnt;
            tempIndex++;

            if(tempIndex >= listSize)
                break;

            tempR = spiralPoints.get(tempIndex).r;
            tempC = spiralPoints.get(tempIndex).c;
            temp[tempR][tempC] = currNum;
            tempIndex++;

            // 그 다음 구간의 시작값으로 변경해줍니다.
            currentIndex = endindex + 1;
        }
    }

    private static void bombAndFill() {
        boolean flag = true;

        while (flag) {
            flag = bomb();
        }

        fill();
    }

    // 4번 이상 반복하여 나오는 구간 제거하는 함수
    private static boolean bomb() {
        boolean flag = false;
        int currentIndex = 0;

        while (currentIndex < spiralPoints.size()) {
            int endIndex = getEndIndex(currentIndex);
            int currentNum = getNumCurrentIndex(currentIndex);

            if (currentNum == 0) {
                break;
            }

            if (endIndex - currentIndex + 1 >= 4) {
                remove(currentIndex, endIndex);
                flag = true;
            }

            currentIndex = endIndex + 1;

        }

        return flag;

    }

    private static void remove(int currentIndex, int endIndex) {
        for(int i=currentIndex; i<=endIndex;i++) {
            int r = spiralPoints.get(i).r;
            int c = spiralPoints.get(i).c;

            score += arr[r][c];
            arr[r][c] =0;
        }
    }

    private static int getNumCurrentIndex(int currentIndex) {
        int r = spiralPoints.get(currentIndex).r;
        int c = spiralPoints.get(currentIndex).c;
        return arr[r][c];
    }

    private static int getEndIndex(int currentIndex) {
        int endIndex = currentIndex + 1;
        int currentNum = getNumCurrentIndex(currentIndex);

        while (endIndex < spiralPoints.size()) {
            if (getNumCurrentIndex(endIndex) == currentNum) {
                endIndex++;
            } else {
                break;
            }
        }
        return endIndex - 1;
    }

    private static void attack(int d, int p) {
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};

        // d방향으로 p마리의 몬스터 제거
        // 제거한 몬스터 값 스코어에 추가
        int centerR = n / 2;
        int centerC = n / 2;
        for (int i = 1; i <= p; i++) {
            int nr = centerR + dr[d] * i;
            int nc = centerC + dc[d] * i;

            score += arr[nr][nc];
            arr[nr][nc] = 0;
        }

        // 공격후 빈자리 채우기
        fill();

    }

    private static void fill() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = 0;
            }
        }

        // 나선형으로 탐색하면서 0이 아닌 값들을 temp에 채워주고
        // 이를 격자 배열로 옮겨준다
        int tempIndex = 0;
        for (Point point : spiralPoints) {
            int arrR = point.r;
            int arrC = point.c;

            if (arr[arrR][arrC] > 0) {
                int tempR = spiralPoints.get(tempIndex).r;
                int tempC = spiralPoints.get(tempIndex).c;
                temp[tempR][tempC] = arr[arrR][arrC];
                tempIndex++;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = temp[i][j];
            }
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        temp = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] strArr = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(strArr[j]);
            }
        }

        // 나선 모양 순서로 지나는 좌표들을 기록해둔다.
        searchSpiral();
        System.out.println(spiralPoints.toString());
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            simulate(d, p);

        }

        System.out.println(score);

    }


}
