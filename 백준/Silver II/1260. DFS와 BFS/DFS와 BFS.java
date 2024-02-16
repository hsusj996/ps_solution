import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, V;
	static StringBuilder result = new StringBuilder();
	static List<Integer>[] list;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			list[start].add(end);
			list[end].add(start);
		}
		
		for(int i=1;i<=N;i++) {
			Collections.sort(list[i]);
		}

		visited = new boolean[N + 1];
		visited[V] = true;
		dfs(V);
		result.append("\n");

		visited = new boolean[N + 1];
		bfs(V);
		result.append("\n");

		System.out.println(result);
	}

	private static void bfs(int v) {
		Queue<Integer> q = new LinkedList<>();
		visited[v] = true;
		q.add(v);

		while (!q.isEmpty()) {
			int cur = q.poll();
			result.append(cur + " ");

			for (int i : list[cur]) {
				if (visited[i]) {
					continue;
				}

				visited[i] = true;
				q.add(i);
			}
		}
	}

	private static void dfs(int v) {
		result.append(v + " ");

		for (int i : list[v]) {
			if (visited[i]) {
				continue;
			}

			visited[i] = true;
			dfs(i);
		}
	}

}