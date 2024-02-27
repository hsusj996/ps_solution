package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class prob1753_2 {
  static class Node {
    int vertex, weight;
    Node next;

    public Node(int vertex, int weight, Node next) {
      super();
      this.vertex = vertex;
      this.weight = weight;
      this.next = next;
    }
  }

  static int[] minDistance;
  static StringTokenizer st = null;
  static int V, E, K;
  static Node[] adjList;
  static boolean[] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());

    V = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(br.readLine());

    visited = new boolean[V + 1];
    adjList = new Node[V + 1];
    minDistance = new int[V + 1];

    Arrays.fill(minDistance, Integer.MAX_VALUE);

    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());

      adjList[from] = new Node(to, weight, adjList[from]);
    }

    dijkstra();

    for (int i = 1; i <= V; i++) {
      System.out.println(minDistance[i] == Integer.MAX_VALUE ? "INF" : minDistance[i]);
    }
  }

  private static void dijkstra() {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    pq.add(K);
    minDistance[K] = 0;

    while (!pq.isEmpty()) {
      int cur = pq.poll();

      if (visited[cur]) {
        continue;
      }
      visited[cur] = true;

      for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
        int min = minDistance[cur] + temp.weight;
        minDistance[temp.vertex] = Math.min(min, minDistance[temp.vertex]);
        pq.add(temp.vertex);
      }
    }
  }
}
