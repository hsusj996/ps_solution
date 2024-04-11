package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob2623 {
  static StringTokenizer st = null;
  static StringBuilder sb = new StringBuilder();
  static int N, M;
  static int[] inDegree;
  static int[][] graph;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    graph = new int[N + 1][N + 1];
    inDegree = new int[N + 1];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int num = Integer.parseInt(st.nextToken());
      int prev = Integer.parseInt(st.nextToken());
      for (int j = 0; j < num - 1; j++) {
        int next = Integer.parseInt(st.nextToken());

        graph[prev][next] = 1;
        prev = next;
      }
    }

    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (graph[i][j] == 1) {
          inDegree[j]++;
        }
      }
    }

    boolean flag = topologySort();

    if (flag) {
      System.out.println(sb.toString());
    } else {
      System.out.println(0);
    }
  }

  private static boolean topologySort() {
    Queue<Integer> q = new ArrayDeque<>();
    boolean[] visited = new boolean[N + 1];

    for (int i = 1; i <= N; i++) {
      if (inDegree[i] == 0) {
        q.add(i);
        visited[i] = true;
        sb.append(i).append("\n");
      }
    }

    while (!q.isEmpty()) {
      int cur = q.poll();

      for (int i = 1; i <= N; i++) {
        if (graph[cur][i] == 1) {
          if (visited[i]) {
            continue;
          }

          if (--inDegree[i] == 0) {
            q.add(i);
            visited[i] = true;
            sb.append(i).append("\n");
          }
        }
      }
    }

    for (int i = 1; i <= N; i++) {
      if (!visited[i]) {
        return false;
      }
    }

    return true;
  }
}
