import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K, result = 0;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		arr = new int[N][M];

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int r = stoi(st.nextToken());
			int c = stoi(st.nextToken());
			int[][] sticker = new int[r][c];

			for (int i = 0; i < r; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < c; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			find(sticker);
		}

		System.out.println(result);
	}

	static void find(int[][] sticker) {
		int r = sticker.length; // 스티커 세로길이
		int c = sticker[0].length; // 스티커 가로길이

		for (int d = 0; d < 4; d++) { // 0 90 180 270
			
			if (d != 0)
				sticker = rotate(sticker, r, c);
			
			r = sticker.length;
			c = sticker[0].length;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (i + r > N || j + c > M)
						break;

					if (check(r, c, i, j, sticker)) {
						return;
					}
				}
			}
		}
	}

	static int[][] rotate(int[][] sticker, int r, int c) {
		int[][] rotateSticker = new int[c][r];

		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				rotateSticker[j][r - i - 1] = sticker[i][j];

		return rotateSticker;
	}

	static boolean check(int r, int c, int r2, int c2, int[][] sticker) {
		for (int i = r2; i < r2 + r; i++) {
			for (int j = c2; j < c2 + c; j++) {
				if (arr[i][j] == 1 && sticker[i - r2][j - c2] == 1)
					return false;
			}
		}

		for (int i = r2; i < r2 + r; i++) {
			for (int j = c2; j < c2 + c; j++) {
				if (sticker[i - r2][j - c2] == 1) {
					arr[i][j] = 1;
					result++;
				}
			}
		}

		return true;
	}

	static int stoi(String s) {
		return Integer.parseInt(s);
	}

}