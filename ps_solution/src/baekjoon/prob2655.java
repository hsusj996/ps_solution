package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
    blockArr = new Block[N];
    dpHeight = new int[N];
    dpNo = new int[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      blockArr[i] = new Block(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
          Integer.parseInt(st.nextToken()));
    }

    Arrays.sort(blockArr);

    for (int i = 0; i < N; i++) {
      dpNo[i] = dpFunc(i);
    }

    System.out.println(dpHeight[0]);
  }

  private static int dpFunc(int idx) {
    if (dpNo[idx] != 0) {
      return dpNo[idx];
    }

    for (int i = idx + 1; i < N; i++) {
      if (blockArr[i].a < blockArr[idx].a) {
        dpNo[idx] = dpFunc(idx);
        dpHeight[idx] = blockArr[idx].h + dpFunc(i);
        return i;
      }
    }

    dpHeight[idx] = blockArr[idx].h;
    return dpNo[idx] = -1;
  }
}
