package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class prob1162 {
  static final long INF = Long.MAX_VALUE;

  static class Node implements Comparable<Node> {
    int to;
    long weight;

    public Node(int to, long weight) {
      this.to = to;
      this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
      if (this.weight - o.weight > 0) {
        return 1;
      } else if (this.weight == o.weight) {
        return 0;
      } else {
        return -1;
      }
    }
  }

  static StringTokenizer st = null;
  static int N, M, K;
  static ArrayList<Node>[] adjList;
  static long[][] minDistanceDP;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    adjList = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++) {
      adjList[i] = new ArrayList<>();
    }
    minDistanceDP = new long[N + 1][K + 1];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());

      adjList[from].add(new Node(to, weight));
      adjList[to].add(new Node(from, weight));
    }

    // 1. Round0: 최단경로 찾기
    dijkstra();

    // 2. 출력
    System.out.println(minDistanceDP[N][K]);
  }

  private static void dijkstra() {
    Arrays.fill(minDistanceDP[1], 0);
    for (int i = 2; i <= N; i++) {
      Arrays.fill(minDistanceDP[i], INF);
    }

    // 라운드별로 DP 테이블 채우기
    for (int round = 0; round <= K; round++) {
      boolean[] visited = new boolean[N + 1];
      // 다익스트라
      PriorityQueue<Node> pq = new PriorityQueue<>();
      pq.add(new Node(1, 0));
      while (!pq.isEmpty()) {
        Node cur = pq.poll();

        if (visited[cur.to]) {
          continue;
        }
        visited[cur.to] = true;

        for (Node next : adjList[cur.to]) {
          long distance = minDistanceDP[cur.to][round] + next.weight;
          if (round >= 1) {
            long distance2 = minDistanceDP[cur.to][round - 1] + 0;
            distance = Math.min(distance, distance2);
          }
          if (minDistanceDP[next.to][round] > distance) {
            minDistanceDP[next.to][round] = distance;
            pq.add(new Node(next.to, distance));
          }
        }
      }
    }
  }
}
