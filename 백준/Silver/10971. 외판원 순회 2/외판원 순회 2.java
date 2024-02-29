import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st = null;
	static int N;
	static int[][] adjMatrix;
	static boolean[] visited;
	static int weightSum = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		adjMatrix = new int[N][N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited[0] = true;
		DFS(0, 0);

		System.out.println(weightSum);
	}

	private static void DFS(int cur, int sum) {
		if (isAllVisited()) {
			if (adjMatrix[cur][0] == 0) {
				return;
			}
			weightSum = Math.min(weightSum, sum + adjMatrix[cur][0]);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (adjMatrix[cur][i] == 0) {
				continue;
			}

			if (visited[i]) {
				continue;
			}

			visited[i] = true;
			DFS(i, sum + adjMatrix[cur][i]);
			visited[i] = false;
		}

	}

	private static boolean isAllVisited() {
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		return true;
	}
}