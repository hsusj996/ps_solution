import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static StringBuilder result = new StringBuilder();
	static int T;
	static int N;
	static int ans;
	static int[][] tasteMap;
	static boolean[] isSelected;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			ans = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			tasteMap = new int[N][N];
			isSelected = new boolean[N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					tasteMap[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			Combi(-1, 0);

			result.append("#" + test_case + " " + ans + "\n");
		}

		System.out.println(result);
	}

	private static void Combi(int prev, int depth) {
		if (depth == N / 2) {
			int selectedSum = 0;
			int otherSum = 0;
			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					for (int j = i + 1; j < N; j++) {
						if (isSelected[j]) {
							selectedSum += (tasteMap[i][j] + tasteMap[j][i]);
						}
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				if (!isSelected[i]) {
					for (int j = i + 1; j < N; j++) {
						if (!isSelected[j]) {
							otherSum += (tasteMap[i][j] + tasteMap[j][i]);
						}
					}
				}
			}

			ans = Math.min(ans, Math.abs(otherSum - selectedSum));

			return;
		}

		for (int i = prev + 1; i < N; i++) {
			isSelected[i] = true;
			Combi(i, depth + 1);
			isSelected[i] = false;
		}
	}

}