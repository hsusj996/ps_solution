package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob15664 {
  static StringTokenizer st = null;
  static int[] arr;
  static boolean[] visited;
  static int[] selected;
  static LinkedHashSet<String> resultSet = new LinkedHashSet<>();
  static int N, M;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    arr = new int[N];
    visited = new boolean[N];
    selected = new int[M];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(arr);

    dfs(0, 0);

    resultSet.forEach(o -> System.out.println(o));
  }

  private static void dfs(int depth, int prev) {
    if (depth == M) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < M; i++) {
        sb.append(selected[i]).append(" ");
      }
      resultSet.add(sb.toString());

      return;
    }

    for (int i = prev; i < N; i++) {
      if (visited[i]) {
        continue;
      }

      visited[i] = true;
      selected[depth] = arr[i];
      dfs(depth + 1, i + 1);
      visited[i] = false;
    }
  }
}
