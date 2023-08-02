import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // this가 p보다 우선순위가 더 높은지 판단합니다.
    public boolean isHigher(Pair p) {
        // 행은 클수록 좋습니다.
        if(this.x != p.x)
            return this.x > p.x;
        // 열은 작을수록 좋습니다.
        return this.y < p.y;
    }
}

class Bundle {
    int BombCount, redCnt, stdX, stdY;

    public Bundle(int BombCount, int redCnt, int stdX, int stdY) {
        this.BombCount = BombCount;
        this.redCnt = redCnt;
        this.stdX = stdX;
        this.stdY = stdY;
    }

    // this가 b보다 우선순위가 더 높은지 판단합니다.
    public boolean isHigher(Bundle b) {
        // 폭탄의 개수는 많을수록 좋습니다.
        if(this.BombCount != b.BombCount)
            return this.BombCount > b.BombCount;
        // 빨간색의 개수는 적을수록 좋습니다.
        if(this.redCnt != b.redCnt)
            return this.redCnt < b.redCnt;
        // 행은 클수록 좋습니다.
        if(this.stdX != b.stdX)
            return this.stdX > b.stdX;
        // 열은 작을수록 좋습니다.
        return this.stdY < b.stdY;
    }
}

public class Codetree20212 {
    public static final Bundle EMPTY_BUNDLE = new Bundle(-1, -1, -1, -1);
    public static final int EMPTY = -2;
    public static final int ROCK = -1;
    public static final int RED = 0;
    public static final int DIR_NUM = 4;
    public static final int MAX_N = 20;

    public static int n, m;

    public static int[][] grid = new int[MAX_N][MAX_N];
    public static int[][] temp = new int[MAX_N][MAX_N];

    public static Queue<Pair> bfsQ = new LinkedList<>();
    public static boolean[][] visited = new boolean[MAX_N][MAX_N];

    public static int ans;

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    // 같은 색이거나, 빨간색 폭탄인 경우에만 이동이 가능합니다.
    public static boolean canGo(int r, int c, int color) {
        return inRange(r, c) && !visited[r][c] && (
                grid[r][c] == color || grid[r][c] == RED
        );
    }

    public static void bfs(int r, int c, int color) {
        // visited 값을 초기화합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                visited[i][j] = false;

        // 시작점을 표시합니다.
        visited[r][c] = true;
        bfsQ.add(new Pair(r, c));

        int[] dr = new int[]{0, 1, 0, -1};
        int[] dc = new int[]{1, 0, -1, 0};

        // BFS 탐색을 수행합니다.
        while(!bfsQ.isEmpty()) {
            Pair currPos = bfsQ.poll();
            int currX = currPos.x, currY = currPos.y;

            for(int i = 0; i < DIR_NUM; i++) {
                int newX = currX + dr[i];
                int newY = currY + dc[i];

                if(canGo(newX, newY, color)) {
                    bfsQ.add(new Pair(newX, newY));
                    visited[newX][newY] = true;
                }
            }
        }
    }

    // (x, y) 지점을 시작으로 bundle 정보를 계산해 반환합니다.
    public static Bundle getBundle(int x, int y) {
        // Step1. (x, y)를 시작으로 bfs 탐색을 진행합니다.
        bfs(x, y, grid[x][y]);

        // Step2. bundle 정보를 계산해 반환합니다.
        int bombCnt = 0, redCnt = 0;
        Pair standard = new Pair(-1, -1);

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) {
                if(!visited[i][j])
                    continue;

                bombCnt++;

                if(grid[i][j] == RED)
                    redCnt++;
                else if(new Pair(i, j).isHigher(standard))
                    standard = new Pair(i, j);
            }

        int stdX = standard.x, stdY = standard.y;
        return new Bundle(bombCnt, redCnt, stdX, stdY);
    }

    // 우선순위에 따라 쉽게 계산하기 위해
    // (폭탄 묶음의 크기, -빨간색 폭탄의 수, 행 번호, -열 번호)
    // 순서대로 값을 넣어줍니다.
    public static Bundle findBestBundle() {
        Bundle bestBundle = EMPTY_BUNDLE;

        // 빨간색이 아닌 폭탄들에 대해서만 전부 탐색합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(grid[i][j] >= 1) {
                    Bundle bundle = getBundle(i, j);
                    if(bundle.isHigher(bestBundle))
                        bestBundle = bundle;
                }

        return bestBundle;
    }

    public static void remove(int col) {
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(visited[i][j]) {
                    assert(grid[i][j] == col || grid[i][j] == RED);
                    grid[i][j] = EMPTY;
                }
    }

    public static void gravity() {
        // Step1.
        // 중력 작용을 쉽게 구현하기 위해
        // temp 배열을 활용합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                temp[i][j] = EMPTY;

        // Step2.
        for(int j = 0; j < n; j++) {
            // 아래에서 위로 올라오면서
            // 해당 위치에 폭탄이 있는 경우에만 temp에
            // 쌓아주는 식으로 코드를 작성할 수 있습니다.

            // 단, 돌이 있는 경우에는
            // 위에부터 쌓일 수 있도록 합니다.
            int lastIdx = n - 1;
            for(int i = n - 1; i >= 0; i--) {
                if(grid[i][j] == EMPTY)
                    continue;
                if(grid[i][j] == ROCK)
                    lastIdx = i;
                temp[lastIdx--][j] = grid[i][j];
            }
        }

        // Step3. 다시 temp 배열을 옮겨줍니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = temp[i][j];
    }

    // 반시계 방향으로 90' 만큼 회전합니다.
    public static void rotate() {
        // Step1.
        // 회전 과정을 쉽게 구현하기 위해
        // temp 배열을 활용합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                temp[i][j] = EMPTY;

        // Step2.
        // 기존 격자를 반시계 방향으로 90도 회전했을 때의 결과를
        // temp에 저장해줍니다.
        for(int j = n - 1; j >= 0; j--)
            for(int i = 0; i < n; i++)
                temp[n - 1 - j][i] = grid[i][j];

        // Step3.
        // 다시 temp 배열을 옮겨줍니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = temp[i][j];
    }

    public static void clean(int x, int y) {
        // Step1. (x, y)를 시작으로 지워야할 폭탄 묶음을 표시합니다.
        bfs(x, y, grid[x][y]);

        // Step2. 폭탄들을 전부 지워줍니다.
        remove(grid[x][y]);

        // Step3. 중력이 작용합니다.
        gravity();
    }

    public static boolean simulate() {
        // Step1. 크기가 최대인 폭탄 묶음을 찾습니다.
        Bundle bestBundle = findBestBundle();

        int bombCnt = bestBundle.BombCount;
        int x = bestBundle.stdX;
        int y = bestBundle.stdY;

        // 만약 폭탄 묶음이 없다면, 종료합니다.
        if(bestBundle == EMPTY_BUNDLE || bombCnt <= 1)
            return false;

        // Step2. 선택된 폭탄 묶음에 해당하는 폭탄들을 전부 제거 후
        //        중력이 작용합니다.
        ans += bombCnt * bombCnt;
        clean(x, y);

        // Step3. 반시계 방향으로 90' 만큼 회전합니다.
        rotate();

        // Step4. 중력이 작용합니다.
        gravity();

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();

        // 끝나기 전까지 시뮬레이션을 반복합니다.
        while(true) {
            boolean keepGoing = simulate();

            if(!keepGoing)
                break;
        }

        System.out.print(ans);
    }
}