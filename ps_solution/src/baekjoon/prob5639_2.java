package baekjoon;

import java.io.*;

public class prob5639_2 {
  static int[] tree = new int[10001];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int idx = 0;
    while (true) {
      String input = br.readLine();

      if (input == null || input.equals("")) {
        break;
      }

      int node = Integer.parseInt(input);
      tree[idx++] = node;
    }

    PostOrder(0, idx - 1);
  }

  static void PostOrder(int n, int end) {
    if (n > end) {
      return;
    }

    int mid = n + 1;
    while (mid <= end && tree[mid] < tree[n]) {
      mid++;
    }

    PostOrder(n + 1, mid - 1);
    PostOrder(mid, end);
    System.out.println(tree[n]);
  }
}
