package baekjoon;

import java.io.*;
import java.util.*;

public class prob11779 {
	// 경로 정보 클래스
	static class RouteInfo {
		String Route;
		int MinCost;

		public RouteInfo(String Route, int MinCost) {
			this.Route = Route;
			this.MinCost = MinCost;
		}

		// 최단 경로 갱신
		public void UpdateRoute(String NewRoute, int idx) {
			this.Route = NewRoute + Integer.toString(idx) + " ";
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			int cnt = Route.split(" ").length;

			sb.append(this.MinCost + "\n");
			sb.append(cnt + "\n");
			sb.append(this.Route);

			return sb.toString();
		}

	}

	// 버스 클래스
	static class edge implements Comparable<edge> {
		int to;
		int cost;

		public edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(edge o) {
			return AllRouteInfo[this.to].MinCost - AllRouteInfo[o.to].MinCost;
		}
	}

	static final int INF = 1_000_000_000;
	static int N;
	static int M;
	static ArrayList<edge>[] arr_edge;
	static int start;
	static int dest;
	static RouteInfo[] AllRouteInfo;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		arr_edge = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			arr_edge[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int city1 = Integer.parseInt(st.nextToken());
			int city2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			arr_edge[city1].add(new edge(city2, cost));
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		dest = Integer.parseInt(st.nextToken());
		
		// 초기화 (기본 출발 경로, 경로 비용 최댓값)
		AllRouteInfo = new RouteInfo[N + 1];
		for (int i = 1; i <= N; i++) {
			AllRouteInfo[i] = new RouteInfo(Integer.toString(start) + " ", INF);
		}

		visited = new boolean[N + 1];

		dijkstra();

		System.out.println(AllRouteInfo[dest].toString());
	}

	public static void dijkstra() {
		PriorityQueue<edge> pq = new PriorityQueue<>();
		pq.add(new edge(start, 0));
		AllRouteInfo[start].MinCost = 0;

		while (!pq.isEmpty()) {
			edge now = pq.poll();

			if (visited[now.to]) {
				continue;
			}
			visited[now.to] = true;

			for (int i = 0; i < arr_edge[now.to].size(); i++) {
				edge next = arr_edge[now.to].get(i);

				int min = AllRouteInfo[now.to].MinCost + next.cost;
				if (AllRouteInfo[next.to].MinCost > min) {
					AllRouteInfo[next.to].MinCost = min;
					AllRouteInfo[next.to].UpdateRoute(AllRouteInfo[now.to].Route, next.to);
				}

				pq.add(next);
			}
		}
	}
}
