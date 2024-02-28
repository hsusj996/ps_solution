import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int x;
		int y;
		int cnt;
		int depth;

		public Node(int x, int y, int cnt, int depth) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.depth = depth;
		}
	}

	static StringTokenizer st = null;
	static int K;
	static int W;
	static int H;
	static int[][] board;
	static boolean[][][] visited;
	static Queue<Node> q;
	static int[][] d_Monkey = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };
	static int[][] d_Horse = { { -2, -1, 1, 2, 2, 1, -1, -2 }, { 1, 2, 2, 1, -1, -2, -2, -1 } };
	static final int INF = 1_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		q = new ArrayDeque<>();
		board = new int[H][W];
		visited = new boolean[H][W][K + 1];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited[0][0][0] = true;
		q.add(new Node(0, 0, 0, 0));

		System.out.println(BFS());
	}

	private static boolean IsOutBound(int x, int y) {
		return x < 0 || x >= H || y < 0 || y >= W;
	}

	private static int BFS() {
		while (!q.isEmpty()) {
			Node cur = q.poll();

			if(cur.x == H-1 && cur.y == W-1) {
				return cur.depth;
			}
			
			for (int d = 0; d < 4; d++) {
				int newX = cur.x + d_Monkey[0][d];
				int newY = cur.y + d_Monkey[1][d];

				if (IsOutBound(newX, newY) || board[newX][newY] == 1 || visited[newX][newY][cur.cnt]) {
					continue;
				}

				visited[newX][newY][cur.cnt] = true;
				q.add(new Node(newX, newY, cur.cnt, cur.depth + 1));
			}
			
			if(cur.cnt >= K) {
				continue;
			}
			
			for (int d = 0; d < 8; d++) {
				int newX = cur.x + d_Horse[0][d];
				int newY = cur.y + d_Horse[1][d];

				if (IsOutBound(newX, newY) || board[newX][newY] == 1 || visited[newX][newY][cur.cnt + 1]) {
					continue;
				}

				visited[newX][newY][cur.cnt + 1] = true;
				q.add(new Node(newX, newY, cur.cnt + 1, cur.depth + 1));
			}
		}
		
		return -1;
	}
}