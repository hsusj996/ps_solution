import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Cdnt {
		int row;
		int col;
		int depth;

		public Cdnt(int row, int col, int depth) {
			super();
			this.row = row;
			this.col = col;
			this.depth = depth;
		}

	}

	static int N, M, D;
	static int[][] map;
	static int max = Integer.MIN_VALUE;
	static int enemyCnt = 0;
	static int[] d_row = { 0, -1, 0 };
	static int[] d_col = { -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					enemyCnt++;
				}
			}
		}

		SetArchers(0, 0);

		System.out.println(max);
	}

	private static void SetArchers(int prev, int depth) {
		if (depth == 3) {
			Simulation();
			return;
		}

		for (int i = prev; i < M; i++) {
			map[N][i] = 2;
			SetArchers(i + 1, depth + 1);
			map[N][i] = 0;
		}
	}

	private static void Simulation() {
		int cnt = enemyCnt;
		int killCnt = 0;
		int[][] CopyMap = new int[N + 1][M];

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				CopyMap[i][j] = map[i][j];
			}
		}

		while (cnt > 0) {
			List<Cdnt> killList = new ArrayList<>();
			for (int i = 0; i < M; i++) {
				if (CopyMap[N][i] == 2) {
					Cdnt AttackedEnemy = bfs(new Cdnt(N, i, 0), CopyMap);
					if (AttackedEnemy == null) {
						continue;
					}
					boolean flag = true;
					for (Cdnt c : killList) {
						if (c.row == AttackedEnemy.row && c.col == AttackedEnemy.col) {
							flag = false;
							break;
						}
					}

					if (flag) {
						killList.add(AttackedEnemy);
					}
				}
			}

			for (Cdnt c : killList) {
				CopyMap[c.row][c.col] = 0;
				cnt--;
				killCnt++;
			}

			cnt -= MoveEnemy(CopyMap);
		}

		max = Math.max(max, killCnt);
	}

	private static int MoveEnemy(int[][] map) {
		int ret = 0;

		for (int i = 0; i < M; i++) {
			if (map[N - 1][i] == 1) {
				ret++;
			}
		}

		for (int i = N - 1; i > 0; i--) {
			map[i] = map[i - 1];
		}
		map[0] = new int[M];

		return ret;
	}

	private static Cdnt bfs(Cdnt cdnt, int[][] map) {
		boolean[][] visited = new boolean[N + 1][M];
		Queue<Cdnt> q = new LinkedList<>();
		q.add(cdnt);
		visited[cdnt.row][cdnt.col] = true;

		while (!q.isEmpty()) {
			Cdnt cur = q.poll();

			if (cur.depth >= D) {
				return null;
			}

			for (int i = 0; i < 3; i++) {
				int newRow = cur.row + d_row[i];
				int newCol = cur.col + d_col[i];

				if (IsOutBound(newRow, newCol) || visited[newRow][newCol]) {
					continue;
				}
				
				if (map[newRow][newCol] == 1) {
					return new Cdnt(newRow, newCol, cur.depth + 1);
				}

				visited[newRow][newCol] = true;
				q.add(new Cdnt(newRow, newCol, cur.depth + 1));
			}
		}

		return null;
	}

	private static boolean IsOutBound(int row, int col) {
		return row < 0 || row >= N || col < 0 || col >= M;
	}
}