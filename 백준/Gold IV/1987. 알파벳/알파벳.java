import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int max = Integer.MIN_VALUE;
	static char[][] map;
	static boolean[][] visited;
	static boolean[] alphaVisited;
	static int R, C;
	static int[] d_row = { -1, 0, 1, 0 };
	static int[] d_col = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visited = new boolean[R][C];
		alphaVisited = new boolean[27];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		visited[0][0] = true;
		alphaVisited[map[0][0] - 'A'] = true;
		dfs(0, 0, 1);

		System.out.println(max);
	}

	private static void dfs(int row, int col, int depth) {
		max = Math.max(max, depth);
		for (int i = 0; i < 4; i++) {
			int newRow = row + d_row[i];
			int newCol = col + d_col[i];

			if (IsOutBound(newRow, newCol) || visited[newRow][newCol] || alphaVisited[map[newRow][newCol] - 'A']) {
				continue;
			}

			visited[newRow][newCol] = true;
			alphaVisited[map[newRow][newCol] - 'A'] = true;
			dfs(newRow, newCol, depth + 1);
			visited[newRow][newCol] = false;
			alphaVisited[map[newRow][newCol] - 'A'] = false;
		}
	}

	private static boolean IsOutBound(int row, int col) {
		return row < 0 || row >= R || col < 0 || col >= C;
	}

}