package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob2342 {
  static StringTokenizer st = null;
  static int[][][] dp;
  static Queue<Integer> q = new ArrayDeque<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    st = new StringTokenizer(br.readLine());
    while (true) {
      int n = Integer.parseInt(st.nextToken());
      if (n == 0) {
        break;
      }

      q.add(n);
    }

    dp = new int[5][5][q.size() + 1];
  }
}
