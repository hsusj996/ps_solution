package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class prob2655 {
  static class Block implements Comparable<Block> {
    int no;
    int a;
    int h;
    int m;

    public Block(int no, int a, int h, int m) {
      this.no = no;
      this.a = a;
      this.h = h;
      this.m = m;
    }

    @Override
    public int compareTo(Block o) {
      return o.m - this.m;
    }

  }

  static int N;
  static Block[] blockArr;
  static int[] dpHeight;
  static int[] dpNo;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = null;
    N = Integer.parseInt(br.readLine());
    blockArr = new Block[N + 1];
    blockArr[0] = new Block(0, 0, 0, 10001);
    dpHeight = new int[N + 1];
    dpNo = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      blockArr[i] = new Block(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
          Integer.parseInt(st.nextToken()));
    }

    Arrays.sort(blockArr);

    for (int i = 1; i <= N; i++) {
      if (dpHeight[i] == 0) {
        dpHeight[i] = dpFunc(i);
      }
    }

    int maxHeight = 0;
    int maxIdx = 0;
    for (int i = 1; i <= N; i++) {
      if (dpHeight[i] > maxHeight) {
        maxHeight = dpHeight[i];
        maxIdx = i;
      }
    }

    Stack<Integer> stk = new Stack<>();
    for (int cur = maxIdx; cur != -1; cur = dpNo[cur]) {
      stk.push(blockArr[cur].no);
    }

    System.out.println(stk.size());
    while (!stk.isEmpty()) {
      System.out.println(stk.pop());
    }
  }

  private static int dpFunc(int idx) {
    for (int i = idx + 1; i <= N; i++) {
      if (blockArr[i].a < blockArr[idx].a) {
        if (dpHeight[i] == 0) {
          dpHeight[i] = dpFunc(i);
        }

        if (dpHeight[idx] < dpHeight[i] + blockArr[idx].h) {
          dpNo[idx] = i;
          dpHeight[idx] = dpHeight[i] + blockArr[idx].h;
        }
      }
    }

    if (dpNo[idx] != 0) {
      return dpHeight[idx];
    }

    dpNo[idx] = -1;
    return dpHeight[idx] = blockArr[idx].h;
  }
}