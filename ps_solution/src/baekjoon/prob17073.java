package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob17073 {
  static class Node {
    int num;
    int water;

    public Node(int num, int water) {
      this.num = num;
      this.water = water;
    }

  }

  static StringTokenizer st = null;
  static int N, W;
  static List<Integer>[] nodeList;
  static boolean[] visited;
  static int cnt = 0;
  static int sum = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    W = Integer.parseInt(st.nextToken());

    visited = new boolean[N + 1];
    nodeList = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++) {
      nodeList[i] = new ArrayList<>();
    }
    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());

      nodeList[u].add(v);
      nodeList[v].add(u);
    }

    // BFS 수행
    Queue<Node> q = new ArrayDeque<>();
    q.add(new Node(1, 20));
    visited[1] = true;
    while (!q.isEmpty()) {
      Node cur = q.poll();

      int childCnt = 0;
      for (int c : nodeList[cur.num]) {
        if (visited[c]) {
          continue;
        }

      }
    }
  }
}
