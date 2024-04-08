package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class prob2143 {
  static class p {
    int[] p1;
    int[] p2;

    public p(int[] p1, int[] p2) {
      this.p1 = p1;
      this.p2 = p2;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + Arrays.hashCode(p1);
      result = prime * result + Arrays.hashCode(p2);
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof p)) {
        return false;
      }

      p o = (p) obj;
      if (this.p1[0] == o.p1[0] && this.p1[1] == o.p1[1] &&
          this.p2[0] == o.p2[0] && this.p2[1] == o.p2[1]) {
        return true;
      }
      return false;
    }

  }

  static StringTokenizer st = null;
  static int T;
  static int n, m;
  static int[] arr1;
  static int[] arr2;
  static int[] p1 = new int[2];
  static int[] p2 = new int[2];
  static HashSet<p> pointSet = new HashSet<>();
  static int cnt = 0;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());

    n = Integer.parseInt(br.readLine());
    arr1 = new int[n + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr1[i] = Integer.parseInt(st.nextToken());
    }

    m = Integer.parseInt(br.readLine());
    arr2 = new int[m + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < m; i++) {
      arr2[i] = Integer.parseInt(st.nextToken());
    }

    p1[0] = p1[1] = 0;
    p2[0] = p2[1] = 0;

    dfs(arr1[0] + arr2[0]);

    System.out.println(pointSet.size());
  }

  private static void dfs(int sum) {
    cnt++;
    if (p1[0] > p1[1] || p2[0] > p2[1] || p1[1] >= n || p2[1] >= m) {
      return;
    }

    if (sum == T) {
      pointSet.add(new p(p1.clone(), p2.clone()));
    }

    if (sum < T) {
      // p1 right 포인터 키우기
      if (p1[0] <= p1[1] + 1 && p2[0] <= p2[1] && p1[1] + 1 < n && p2[1] < m) {
        dfs(sum + arr1[++p1[1]]);
        p1[1]--;
      }
      // p2 right 포인터 키우기
      if (p1[0] <= p1[1] && p2[0] <= p2[1] + 1 && p1[1] < n && p2[1] + 1 < m) {
        dfs(sum + arr2[++p2[1]]);
        p2[1]--;
      }
    } else {
      // p1 left 포인터 키우기
      if (p1[0] + 1 <= p1[1] && p2[0] <= p2[1] && p1[1] < n && p2[1] < m) {
        dfs(sum - arr1[p1[0]++]);
        p1[0]--;
      }
      // p2 left 포인터 키우기
      if (p1[0] <= p1[1] && p2[0] + 1 <= p2[1] && p1[1] < n && p2[1] < m) {
        dfs(sum - arr2[p2[0]++]);
        p2[0]--;
      }
    }
  }
}
