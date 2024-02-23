import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st = null;
	static int maxScore = 0;
	static int[] order = new int[10];
	static int[][] inningScoreInfo;
	static boolean[] visited = new boolean[10];
	static int N;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		inningScoreInfo = new int[N + 1][10];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				inningScoreInfo[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited[1] = true;
		order[4] = 1;

		Permutation(1);

		System.out.println(maxScore);
	}

	private static void Permutation(int depth) {
		if (depth == 10) {
			maxScore = Math.max(maxScore, Simulation());

			return;
		}

		if (depth == 4) {
			Permutation(depth + 1);
		} else {
			for (int i = 2; i <= 9; i++) {
				if (visited[i]) {
					continue;
				}

				visited[i] = true;
				order[depth] = i;
				Permutation(depth + 1);
				visited[i] = false;
			}
		}
	}

	private static int Simulation() {
		int ret = 0;
		int hitterNum = 1;

		for (int i = 1; i <= N; i++) {
			boolean[] base = new boolean[4];
			int outCnt = 0;
			
			while (outCnt < 3) {
				if (inningScoreInfo[i][order[hitterNum]] == 0) {
					outCnt++;
				} else {
					base[0] = true;
					for (int j = 3; j >= 0; j--) {
						if (base[j]) {
							int dest = j + inningScoreInfo[i][order[hitterNum]];
							if (dest >= 4) {
								base[j] = false;
								ret++;
							} else {
								base[dest] = true;
								base[j] = false;
							}
						}
					}
				}
				if (++hitterNum == 10) {
					hitterNum = 1;
				}
			}
		}
		return ret;
	}

}