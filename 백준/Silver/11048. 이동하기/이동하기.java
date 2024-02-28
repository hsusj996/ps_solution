import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st = null;
	static int[][] board;
	static int[][] DP;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		DP = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		DP[0][0] = board[0][0];
		for (int i = 1; i < M; i++) {
			DP[0][i] = DP[0][i - 1] + board[0][i];

		}
		for (int i = 1; i < N; i++) {
			DP[i][0] = DP[i - 1][0] + board[i][0];
		}

		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				int max = Math.max(DP[i - 1][j - 1], Math.max(DP[i - 1][j], DP[i][j - 1]));
				DP[i][j] = max + board[i][j];
			}
		}

		System.out.println(DP[N - 1][M - 1]);
	}

}