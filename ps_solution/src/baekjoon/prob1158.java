package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob1158 {
  static int N;
  static int K;
  static StringBuilder result = new StringBuilder();
  static Queue<Integer> q = new LinkedList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    for (int i = 1; i <= N; i++) {
      q.add(i);
    }

    result.append("<");
    int cnt = 1;

    while (q.size() > 1) {
      cnt %= K;
      int n = q.poll();
      if (cnt == 0) {
        result.append(n).append(", ");
      } else {
        q.add(n);
      }

      cnt++;
    }

    result.append(q.poll()).append(">");

    System.out.println(result);
  }
}
