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

public class prob16681_2 {
  static final long INF = Long.MAX_VALUE;

  static class Edge implements Comparable<Edge> {
    int to;
    long weight;

    public Edge(int to, long weight) {
      this.to = to;
      this.weight = weight;
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
  static ArrayList<Edge>[] edgeList;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    D = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());

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

      edgeList[v1].add(new Edge(v2, weight));
      edgeList[v2].add(new Edge(v1, weight));
    }

    // 1에서 최단경로
    PriorityQueue<Edge> pq = new PriorityQueue<>();
    pq.add(new Edge(1, 0));
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
          pq.add(new Edge(c.to, distance));
        }
      }
    }

    // N - 1에서 최단경로
    pq = new PriorityQueue<>();
    pq.add(new Edge(N, 0));
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
          pq.add(new Edge(c.to, distance));
        }
      }
    }

    long maxValue = (-1) * INF;
    for (int i = 1; i <= N - 1; i++) {
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
