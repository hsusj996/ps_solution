import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T;
	static int H, W, N;
	static char[][] map;
	static int[] d_row = { -1, 1, 0, 0 };
	static int[] d_col = { 0, 0, -1, 1 };
	static String cmd;
	static StringBuilder result = new StringBuilder();
	static int myRow, myCol, myDirection;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];

			for (int i = 0; i < H; i++) {
				String s = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = s.charAt(j);
					if (map[i][j] == '<' || map[i][j] == '^' || map[i][j] == '>' || map[i][j] == 'v') {
						if (map[i][j] == '^') {
							myDirection = 0;
						} else if (map[i][j] == 'v') {
							myDirection = 1;
						} else if (map[i][j] == '<') {
							myDirection = 2;
						} else if (map[i][j] == '>') {
							myDirection = 3;
						}
						myRow = i;
						myCol = j;
					}
				}
			}

			N = Integer.parseInt(br.readLine());
			cmd = br.readLine();
			for (int i = 0; i < N; i++) {
				Simulation(cmd.charAt(i));
			}

			result.append("#").append(test_case).append(" ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					result.append(map[i][j]);
				}
				result.append("\n");
			}
		}
		System.out.println(result);
	}

	private static void Simulation(char cmd) {
		int newRow = 0;
		int newCol = 0;
		switch (cmd) {
		case 'U':
			map[myRow][myCol] = '.';
			newRow = myRow + d_row[0];
			newCol = myCol + d_col[0];
			myDirection = 0;
			if (isPossible(newRow, newCol)) {
				myRow = newRow;
				myCol = newCol;
			}
			map[myRow][myCol] = '^';
			break;
		case 'D':
			map[myRow][myCol] = '.';
			newRow = myRow + d_row[1];
			newCol = myCol + d_col[1];
			myDirection = 1;
			if (isPossible(newRow, newCol)) {
				myRow = newRow;
				myCol = newCol;
			}
			map[myRow][myCol] = 'v';
			break;
		case 'L':
			map[myRow][myCol] = '.';
			newRow = myRow + d_row[2];
			newCol = myCol + d_col[2];
			myDirection = 2;
			if (isPossible(newRow, newCol)) {
				myRow = newRow;
				myCol = newCol;
			}
			map[myRow][myCol] = '<';
			break;
		case 'R':
			map[myRow][myCol] = '.';
			newRow = myRow + d_row[3];
			newCol = myCol + d_col[3];
			myDirection = 3;
			if (isPossible(newRow, newCol)) {
				myRow = newRow;
				myCol = newCol;
			}
			map[myRow][myCol] = '>';
			break;
		case 'S':
			int shotRow = myRow + d_row[myDirection];
			int shotCol = myCol + d_col[myDirection];

			while (true) {
				if (isOutBound(shotRow, shotCol)) {
					break;
				}
				if (map[shotRow][shotCol] == '#') {
					break;
				}
				if (map[shotRow][shotCol] == '*') {
					map[shotRow][shotCol] = '.';
					break;
				}
				shotRow += d_row[myDirection];
				shotCol += d_col[myDirection];
			}
			break;
		}
	}

	private static boolean isOutBound(int row, int col) {
		return row < 0 || row >= H || col < 0 || col >= W;
	}

	private static boolean isPossible(int row, int col) {
		return !isOutBound(row, col) && map[row][col] == '.';
	}

}