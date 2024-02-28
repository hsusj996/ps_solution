import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st = null;
	static int N;
	static int[][] board;
	static int[][][] DP;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		DP = new int[N][N][3];

		DP[0][1][0] = 1;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 2; i < N; i++) {
			if (board[0][i] == 0) {
				DP[0][i][0] = DP[0][i - 1][0];
			}
			if (board[0][i] == 1) {
				DP[0][i][0] = 0;
			}
		}

		for (int i = 1; i < N; i++) {
			for (int j = 2; j < N; j++) {
				if (board[i][j] == 1) {
					DP[i][j][0] = DP[i][j][1] = DP[i][j][2] = 0;
					continue;
				}

				DP[i][j][0] = DP[i][j - 1][0] + DP[i][j - 1][1];
				DP[i][j][2] = DP[i - 1][j][2] + DP[i - 1][j][1];
				if (board[i - 1][j] == 1 || board[i][j - 1] == 1) {
					continue;
				}

				DP[i][j][1] = DP[i - 1][j - 1][0] + DP[i - 1][j - 1][1] + DP[i - 1][j - 1][2];
			}
		}

		System.out.println(DP[N - 1][N - 1][0] + DP[N - 1][N - 1][1] + DP[N - 1][N - 1][2]);
	}

}