import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] graph;
	static int[] popArr;
	static boolean[] isSelected;
	static int popSum = 0;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		popArr = new int[N + 1];
		graph = new int[N + 1][N + 1];
		isSelected = new boolean[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			popArr[i] = Integer.parseInt(st.nextToken());
			popSum += popArr[i];
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());

			for (int j = 0; j < n; j++) {
				graph[i][Integer.parseInt(st.nextToken())] = 1;
			}
		}

		PowerSet(1);

		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	private static void PowerSet(int depth) {
		if (depth == N + 1) {
			int cnt = 0;
			int sumA = 0;
			for (int i = 1; i <= N; i++) {
				if (isSelected[i]) {
					cnt++;
					sumA += popArr[i];
				}
			}

			if (cnt == 0 || cnt == N) {
				return;
			}

			boolean[] visitedA = new boolean[N + 1];
			boolean[] visitedB = new boolean[N + 1];

			for (int i = 1; i <= N; i++) {
				if (isSelected[i]) {
					visitedA = bfs(i, true);
					break;
				}
			}
			for (int i = 1; i <= N; i++) {
				if (!isSelected[i]) {
					visitedB = bfs(i, false);
					break;
				}
			}

			for (int i = 1; i <= N; i++) {
				if(isSelected[i]) {
					if(!visitedA[i]) {
						return;
					}
				}
				else {
					if(!visitedB[i]) {
						return;
					}
				}
			}

			int sumB = popSum - sumA;
			min = Math.min(min, Math.abs(sumA - sumB));

			return;
		}

		isSelected[depth] = true;
		PowerSet(depth + 1);
		isSelected[depth] = false;
		PowerSet(depth + 1);
	}

	private static boolean[] bfs(int start, boolean select) {
		boolean[] ret = new boolean[N + 1];
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		ret[start] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int j = 1; j <= N; j++) {
				if (graph[cur][j] == 0) {
					continue;
				}

				if (ret[j]) {
					continue;
				}

				if (isSelected[j] != select) {
					continue;
				}

				q.add(j);
				ret[j] = true;
			}
		}

		return ret;
	}
}