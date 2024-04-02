package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob2263 {
  static StringTokenizer st = null;
  static int N;
  static int[] postOrder;
  static int[] inOrder;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    postOrder = new int[N];
    inOrder = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      inOrder[i] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      postOrder[i] = Integer.parseInt(st.nextToken());
    }

    PreOrder(0, N - 1, N - 1);

    System.out.println(sb.toString());
  }

  private static void PreOrder(int from, int to, int curPointer) {
    if (from > to) {
      return;
    }

    int cur = postOrder[curPointer];
    int curPos = -1;
    for (int i = from; i <= to; i++) {
      if (inOrder[i] == cur) {
        curPos = i;
        break;
      }
    }

    int leftPointer = curPointer - (to - curPos) - 1;
    int rightPointer = curPointer - 1;

    sb.append(cur).append(" ");
    PreOrder(from, curPos - 1, leftPointer);
    PreOrder(curPos + 1, to, rightPointer);
  }
}
