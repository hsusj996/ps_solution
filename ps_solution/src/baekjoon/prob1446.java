package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class prob1446 {
  static class ShortCut implements Comparable<ShortCut> {
    int start;
    int end;
    int distance;

    public ShortCut(int start, int end, int distance) {
      this.start = start;
      this.end = end;
      this.distance = distance;
    }

    @Override
    public int compareTo(ShortCut o) {
      if (this.start == o.start) {
        return this.end - o.end;
      } else {
        return this.start - o.start;
      }
    }

  }

  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st = null;
  static int N, D;
  static ShortCut[] shortcutArr;
  static int min = 10001;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    shortcutArr = new ShortCut[N];

    D = Integer.parseInt(st.nextToken());

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int distance = Integer.parseInt(st.nextToken());

      shortcutArr[i] = new ShortCut(start, end, distance);
    }

    Arrays.sort(shortcutArr);

    dfs(0, 0);

    System.out.println(min);
  }

  private static void dfs(int curPos, int sum) {
    if (sum >= min) {
      return;
    }

    if (curPos == D) {
      min = Math.min(min, sum);
      return;
    }

    for (int i = 0; i < N; i++) {
      if (shortcutArr[i].start < curPos || shortcutArr[i].end > D) {
        continue;
      }

      dfs(shortcutArr[i].end, sum + (shortcutArr[i].start - curPos) + shortcutArr[i].distance);
    }

    dfs(D, sum + (D - curPos));
  }
}
