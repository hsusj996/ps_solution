import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder result = new StringBuilder();
	static StringTokenizer st = null;
	static int T, N, M;
	static long[][] DP;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		DP = new long[31][31];

		DP[0][0] = 1;
		DP[1][0] = 1;
		DP[1][1] = 1;

		for (int i = 2; i <= 30; i++) {
			DP[i][0] = 1;
			DP[i][i] = 1;
			for (int j = 1; j <= i; j++) {
				DP[i][j] = DP[i - 1][j] + DP[i - 1][j - 1];
			}
		}
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			result.append(DP[M][N]).append("\n");
		}

		System.out.println(result);
	}

}