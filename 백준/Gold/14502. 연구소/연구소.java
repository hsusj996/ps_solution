import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static final int[] d_x = { -1, 0, 1, 0 };
	static final int[] d_y = { 0, 1, 0, -1 };

	static class xy {
		int x;
		int y;

		public xy(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static StringTokenizer st = null;
	static int[][] board;
	static boolean[][] visited;
	static List<xy> virusList;
	static int emptyCnt;
	static int maxEmptyCnt;
	static int tmpCnt;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		virusList = new ArrayList<>();
		emptyCnt = 0;
		maxEmptyCnt = 0;

		// 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 0) {
					emptyCnt++;
				} else if (board[i][j] == 2) {
					virusList.add(new xy(i, j));
				}
			}
		}

		// 벽 세우기 (내부에서 시뮬레이션)
		CombiWall(new xy(0, -1), 0);

		System.out.println(maxEmptyCnt);
	}

	private static void CombiWall(xy prev, int depth) {
		if (depth == 3) {
			visited = new boolean[N][M];
			tmpCnt = emptyCnt - 3;
			for (xy c : virusList) {
				BFS(c);
			}

			maxEmptyCnt = Math.max(maxEmptyCnt, tmpCnt);

			return;
		}

		for (int i = prev.y + 1; i < M; i++) {
			if (board[prev.x][i] != 0) {
				continue;
			}
			board[prev.x][i] = 1;
			CombiWall(new xy(prev.x, i), depth + 1);
			board[prev.x][i] = 0;
		}

		for (int i = prev.x + 1; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] != 0) {
					continue;
				}
				board[i][j] = 1;
				CombiWall(new xy(i, j), depth + 1);
				board[i][j] = 0;
			}
		}
	}

	private static void BFS(xy c) {
		Queue<xy> q = new ArrayDeque<>();
		q.add(c);
		visited[c.x][c.y] = true;

		while (!q.isEmpty()) {
			xy cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int newX = cur.x + d_x[i];
				int newY = cur.y + d_y[i];

				if (IsOutBound(newX, newY) || visited[newX][newY] || board[newX][newY] == 1) {
					continue;
				}

				if (board[newX][newY] == 0) {
					tmpCnt--;
				}

				q.add(new xy(newX, newY));
				visited[newX][newY] = true;
			}
		}
	}

	private static boolean IsOutBound(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M;
	}

}