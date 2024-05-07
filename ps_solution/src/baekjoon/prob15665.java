package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class prob15665 {
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st = null;

  static int N, M;
  static int[] arr;
  static boolean[] input = new boolean[10001];
  static ArrayList<Integer> list = new ArrayList<>();
  static int listSize;
  static int[] selected;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      int k = Integer.parseInt(st.nextToken());
      input[k] = true;
    }

    for (int i = 0; i <= 10000; i++) {
      if (input[i]) {
        list.add(i);
      }
    }

    selected = new int[M];
    listSize = list.size();

    dfs(0);

    System.out.println(sb.toString());
  }

  private static void dfs(int depth) {
    if (depth == M) {
      for (int i = 0; i < M; i++) {
        sb.append(selected[i]).append(" ");
      }
      sb.append("\n");
      return;
    }

    for (int i = 0; i < listSize; i++) {
      selected[depth] = list.get(i);
      dfs(depth + 1);
    }
  }
}
