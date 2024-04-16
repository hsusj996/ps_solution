package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob5014 {
  static final int INF = 1_000_000;
  static int F;
  static int S;
  static int G;
  static int U;
  static int D;
  static boolean[] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    F = Integer.parseInt(st.nextToken());
    S = Integer.parseInt(st.nextToken());
    G = Integer.parseInt(st.nextToken());
    U = Integer.parseInt(st.nextToken());
    D = Integer.parseInt(st.nextToken());
    visited = new boolean[F + 1];

    Queue<Integer> q = new ArrayDeque<>();
    q.add(S);
    visited[S] = true;

    int depth = 0;
    boolean findFlag = false;
    while (!q.isEmpty()) {
      int size = q.size();

      while (size-- > 0) {
        int cur = q.poll();
        if (cur == G) {
          findFlag = true;
          break;
        }

        int next1 = cur + U;
        int next2 = cur - D;

        if (next1 <= F && !visited[next1]) {
          q.add(next1);
          visited[next1] = true;
        }
        if (next2 > 0 && !visited[next2]) {
          q.add(next2);
          visited[next2] = true;
        }

      }
      if (findFlag) {
        break;
      }
      depth++;
    }

    if (findFlag) {
      System.out.println(depth);
    } else {
      System.out.println("use the stairs");
    }
  }
}
