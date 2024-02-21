package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class prob13023 {
  static int N, M;
  static List<Integer>[] graph;
  static boolean[] visited;
  static boolean endFlag = false;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    graph = new ArrayList[N];
    for (int i = 0; i < N; i++) {
      graph[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      graph[a].add(b);
      graph[b].add(a);
    }

    visited = new boolean[N];

    for (int i = 0; i < N; i++) {
      if (visited[i]) {
        continue;
      }
      visited[i] = true;
      dfs(i, 0);
      visited[i] = false;
    }

    if (endFlag) {
      System.out.println(1);
    } else {
      System.out.println(0);
    }
  }

  private static void dfs(int cur, int depth) {
    if (depth == 4) {
      endFlag = true;
      return;
    }

    for (int i = 0; i < graph[cur].size(); i++) {
      int next = graph[cur].get(i);

      if (visited[next] || endFlag) {
        continue;
      }

      visited[next] = true;
      dfs(next, depth + 1);
      visited[next] = false;
    }
  }

}
