package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class prob16681 {
  static final long INF = Long.MAX_VALUE;

  static class Edge implements Comparable<Edge> {
    int from;
    int to;
    long weight;

    public Edge(int from, int to, long weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + from;
      result = prime * result + to;
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof Edge)) {
        return false;
      }

      Edge o = (Edge) obj;
      return this.from == o.from && this.to == o.to;
    }

    @Override
    public int compareTo(Edge o) {
      if (this.weight < o.weight) {
        return -1;
      } else if (this.weight == o.weight) {
        return 0;
      } else {
        return 1;
      }
    }
  }

  static StringTokenizer st = null;
  static int N, M, D, E;
  static long[] minDistanceFromStart;
  static long[] minDistanceFromDest;
  static int[] heightArr;
  static List<Edge>[] edgeList;
  static HashMap<Edge, Long> edgeMap;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    D = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());

    edgeMap = new HashMap<>();
    edgeList = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++) {
      edgeList[i] = new ArrayList<>();
    }
    heightArr = new int[N + 1];
    minDistanceFromStart = new long[N + 1];
    Arrays.fill(minDistanceFromStart, INF);
    minDistanceFromDest = new long[N + 1];
    Arrays.fill(minDistanceFromDest, INF);

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      heightArr[i] = Integer.parseInt(st.nextToken());
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int v1 = Integer.parseInt(st.nextToken());
      int v2 = Integer.parseInt(st.nextToken());
      long weight = Long.parseLong(st.nextToken());

      if (v1 == v2) {
        continue;
      }

      Edge e1 = new Edge(v1, v2, weight);
      Edge e2 = new Edge(v2, v1, weight);

      if (edgeMap.containsKey(e1)) {
        if (edgeMap.get(e1) > weight) {
          edgeMap.remove(e1);
          edgeMap.put(e1, weight);
        }
      } else {
        edgeMap.put(e1, weight);
      }
      if (edgeMap.containsKey(e2)) {
        if (edgeMap.get(e2) > weight) {
          edgeMap.remove(e2);
          edgeMap.put(e2, weight);
        }
      } else {
        edgeMap.put(e2, weight);
      }
    }

    // 맵 -> 리스트로
    edgeMap.forEach((e, i) -> edgeList[e.from].add(e));

    // 1에서 최단경로
    PriorityQueue<Edge> pq = new PriorityQueue<>();
    pq.add(new Edge(1, 1, 0));
    minDistanceFromStart[1] = 0;
    int cnt = 0;
    while (!pq.isEmpty()) {
      Edge cur = pq.poll();

      if (minDistanceFromStart[cur.to] < cur.weight) {
        continue;
      }

      for (Edge c : edgeList[cur.to]) {
        long distance = minDistanceFromStart[cur.to] + c.weight;
        if (minDistanceFromStart[c.to] > distance && heightArr[c.to] > heightArr[cur.to]) {
          minDistanceFromStart[c.to] = distance;
          pq.add(new Edge(c.from, c.to, distance));
        }
      }
    }

    // N - 1에서 최단경로
    pq = new PriorityQueue<>();
    pq.add(new Edge(N, N, 0));
    minDistanceFromDest[N] = 0;
    cnt = 0;
    while (!pq.isEmpty()) {
      Edge cur = pq.poll();

      if (minDistanceFromDest[cur.to] < cur.weight) {
        continue;
      }

      for (Edge c : edgeList[cur.to]) {
        long distance = minDistanceFromDest[cur.to] + c.weight;
        if (minDistanceFromDest[c.to] > distance && heightArr[c.to] > heightArr[cur.to]) {
          minDistanceFromDest[c.to] = distance;
          pq.add(new Edge(c.from, c.to, distance));
        }
      }
    }

    long maxValue = (-1) * INF;
    for (int i = 1; i <= N; i++) {
      if (minDistanceFromStart[i] >= INF || minDistanceFromDest[i] >= INF) {
        continue;
      }
      long distance = minDistanceFromStart[i] + minDistanceFromDest[i];

      int height = heightArr[i];
      long v = E * height - D * distance;

      maxValue = Math.max(maxValue, v);
    }

    System.out.println(maxValue == (-1) * INF ? "Impossible" : maxValue);
  }
}
