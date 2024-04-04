package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob20040 {
  static StringTokenizer st = null;
  static int N, M;
  static int result = 0;
  static int[] parents;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    parents = new int[N];
    MakeSet();

    for (int i = 1; i <= M; i++) {
      st = new StringTokenizer(br.readLine());
      int p1 = Integer.parseInt(st.nextToken());
      int p2 = Integer.parseInt(st.nextToken());

      if (!Union(p1, p2)) {
        result = i;
        break;
      }
    }

    System.out.println(result);
  }

  private static boolean Union(int p1, int p2) {
    int aRoot = FindSet(p1);
    int bRoot = FindSet(p2);

    if (aRoot == bRoot) {
      return false;
    }

    parents[bRoot] = aRoot;
    return true;
  }

  private static int FindSet(int a) {
    if (parents[a] == a) {
      return a;
    }

    return parents[a] = FindSet(parents[a]);
  }

  private static void MakeSet() {
    for (int i = 0; i < N; i++) {
      parents[i] = i;
    }
  }
}
