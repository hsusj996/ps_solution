package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class prob1208 {
  static final int INF = 1_000_000_000;

  static class Edge implements Comparable<Edge> {
    int to;
    int weight;

    public Edge(int to, int weight) {
      this.to = to;
      this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
      return this.weight - o.weight;
    }

  }

  static class xy {
    int x;
    int y;

    public xy(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + x;
      result = prime * result + y;
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof xy)) {
        return false;
      }

      xy o = (xy) obj;
      return (this.x == o.x && this.y == o.y) || (this.x == o.y && this.y == o.x);
    }

  }

  static StringTokenizer st = null;
  static List<Edge>[] graph;
  static int[] minDistance;
  static int N, M;
  static PriorityQueue<Edge> pq = new PriorityQueue<>();
  static boolean[] visited;
  static HashMap<xy, Integer> edgeMap = new HashMap<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    graph = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++) {
      graph[i] = new ArrayList<>();
    }

    minDistance = new int[N + 1];
    visited = new boolean[N + 1];

    // 전처리
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());

      xy e = new xy(from, to);
      if (edgeMap.containsKey(e)) {
        if (edgeMap.get(e) > weight) {
          edgeMap.replace(e, weight);
        }
      } else {
        edgeMap.put(e, weight);
      }
    }
    // 맵에서 뽑아서 그래프 구성
    for (Iterator<xy> iterator = edgeMap.keySet().iterator(); iterator.hasNext();) {
      xy cur = iterator.next();
      graph[cur.x].add(new Edge(cur.y, edgeMap.get(cur)));
      graph[cur.y].add(new Edge(cur.x, edgeMap.get(cur)));
    }

    // 다익스트라
    dijkstra();

    System.out.println(minDistance[N]);
  }

  private static void dijkstra() {
    pq.add(new Edge(1, 0));
    Arrays.fill(minDistance, INF);
    minDistance[1] = 0;

    while (!pq.isEmpty()) {
      Edge cur = pq.poll();

      if (visited[cur.to]) {
        continue;
      }
      visited[cur.to] = true;

      for (Edge next : graph[cur.to]) {
        int min = minDistance[cur.to] + next.weight;
        if (min < minDistance[next.to]) {
          minDistance[next.to] = min;
          pq.add(new Edge(next.to, min));
        }
      }
    }
  }
}
