import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R;
	static int C;
	static char[][] map;
	static boolean[][] visited;
	static int cnt = 0;
	static int[] d_row = { -1, 0, 1 };
	static int[] route;
	static boolean flag;

	static class Cdnt {
		int row;
		int col;

		public Cdnt(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visited = new boolean[R][C];
		route = new int[C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		for (int i = 0; i < R; i++) {
			flag = false;
			DFS(new Cdnt(i, 0));
		}

		System.out.println(cnt);
	}

	private static void DFS(Cdnt now) {
		visited[now.row][now.col] = true;
		if (now.col == C - 1) {
			cnt++;
			flag = true;
			return;
		}

		for (int i = 0; i < 3; i++) {
			if (flag) {
				return;
			}
			int nextRow = now.row + d_row[i];
			int nextCol = now.col + 1;

			if (IsPossible(nextRow, nextCol)) {
				DFS(new Cdnt(nextRow, nextCol));
			}
		}
	}

	private static boolean IsPossible(int row, int col) {
		if (row < 0 || row >= R || col < 0 || col >= C) {
			return false;
		}

		if (map[row][col] == 'x' || visited[row][col]) {
			return false;
		}

		return true;
	}

}