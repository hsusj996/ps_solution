package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob17144_2 {
	static int R;
	static int C;
	static int T;
	static int[][] map;
	static int cleanerUp, cleanerDown;
	static int ans = 0;
	static int[] d_row = { -1, 0, 1, 0 };
	static int[] d_col = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[R][C];

		// 입력
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 공기청정기 찾기
		FindCleaner();

		// 시뮬레이션
		while (T-- > 0) {
			map = Dispersion();

			ActivateCleaner();
		}

		// 미세먼지 양 출력
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					ans += map[i][j];
				}
			}
		}
		System.out.println(ans);
	}

	static void ActivateCleaner() {
		for (int row = cleanerUp - 1; row > 0; row--) {
			map[row][0] = map[row - 1][0];
		}

		for (int col = 0; col < C - 1; col++) {
			map[0][col] = map[0][col + 1];
		}

		for (int row = 0; row < cleanerUp; row++) {
			map[row][C - 1] = map[row + 1][C - 1];
		}

		for (int col = C - 1; col > 1; col--) {
			map[cleanerUp][col] = map[cleanerUp][col - 1];
		}

		map[cleanerUp][1] = 0;

		for (int row = cleanerDown + 1; row < R - 1; row++) {
			map[row][0] = map[row + 1][0];
		}

		for (int col = 0; col < C - 1; col++) {
			map[R - 1][col] = map[R - 1][col + 1];
		}

		for (int row = R - 1; row > cleanerDown; row--) {
			map[row][C - 1] = map[row - 1][C - 1];
		}

		for (int col = C - 1; col > 1; col--) {
			map[cleanerDown][col] = map[cleanerDown][col - 1];
		}

		map[cleanerDown][1] = 0;
	}

	static void FindCleaner() {
		for (int i = 0; i < R; i++) {
			if (map[i][0] == -1) {
				cleanerUp = i;
				cleanerDown = i + 1;
				return;
			}
		}
	}

	static int[][] Dispersion() {
		int[][] newMap = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == -1) {
					newMap[i][j] = -1;
					continue;
				}

				newMap[i][j] += map[i][j];
				for (int direction = 0; direction < 4; direction++) {
					int new_row = i + d_row[direction];
					int new_col = j + d_col[direction];

					if (IsOutBound(new_row, new_col) || map[new_row][new_col] == -1) {
						continue;
					}

					newMap[new_row][new_col] += map[i][j] / 5;
					newMap[i][j] -= map[i][j] / 5;
				}
			}
		}

		return newMap;
	}

	static boolean IsOutBound(int row, int col) {
		return row < 0 || row >= R || col < 0 || col >= C;
	}
}
