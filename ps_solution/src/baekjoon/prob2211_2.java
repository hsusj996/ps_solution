package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class prob2211_2 {
  static final int INF = 1_000_000_000;

  static class Edge {
    int v1;
    int v2;

    public Edge(int v1, int v2) {
      this.v1 = v1;
      this.v2 = v2;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + v1;
      result = prime * result + v2;
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof Edge)) {
        return false;
      }

      Edge o = (Edge) obj;

      if (this.v1 == o.v1 && this.v2 == o.v2) {
        return true;
      }
      if (this.v1 == o.v2 && this.v2 == o.v1) {
        return true;
      }

      return false;
    }

  }

  static class Node implements Comparable<Node> {
    int to;
    int weight;

    public Node(int to, int weight) {
      this.to = to;
      this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
      return this.weight - o.weight;
    }
  }

  static int N, M, K;
  static StringTokenizer st = null;
  static List<Node>[] nodeList;
  static boolean[] visited;
  static int[] minDistance;
  static int[] prevPath;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    nodeList = new ArrayList[N + 1];
    minDistance = new int[N + 1];
    Arrays.fill(minDistance, INF);
    visited = new boolean[N + 1];
    prevPath = new int[N + 1];

    for (int i = 1; i <= N; i++) {
      nodeList[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      nodeList[a].add(new Node(b, weight));
      nodeList[b].add(new Node(a, weight));
    }

    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.add(new Node(1, 0));
    minDistance[1] = 0;

    while (!pq.isEmpty()) {
      Node cur = pq.poll();

      if (visited[cur.to]) {
        continue;
      }
      visited[cur.to] = true;

      for (Node c : nodeList[cur.to]) {
        int newDistance = minDistance[cur.to] + c.weight;

        if (newDistance < minDistance[c.to]) {
          minDistance[c.to] = newDistance;
          // prevPath[c.to] = cur.to; // 경로 추적
          pq.add(new Node(c.to, newDistance));
        }
      }
    }

    Set<Edge> edgeSet = new HashSet<>();
    for (int i = 2; i <= N; i++) {
      int cur = i;
      for (; prevPath[cur] != 0; cur = prevPath[cur]) {
        edgeSet.add(new Edge(cur, prevPath[cur]));
      }
    }

    System.out.println(edgeSet.size());
    edgeSet.forEach(e -> System.out.println(e.v1 + " " + e.v2));
    return;
  }
}
