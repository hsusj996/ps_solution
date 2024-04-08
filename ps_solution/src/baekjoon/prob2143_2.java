package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob2143_2 {
  static class E implements Comparable<E> {
    int idx;
    int sum;

    public E(int idx, int sum) {
      this.idx = idx;
      this.sum = sum;
    }

    @Override
    public int compareTo(E o) {
      return this.sum - o.sum;
    }
  }

  static StringTokenizer st = null;
  static int T;
  static int n, m;
  static E[] arr1;
  static E[] arr2;
  static int cnt = 0;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());

    n = Integer.parseInt(br.readLine());
    arr1 = new E[n];
    st = new StringTokenizer(br.readLine());
    arr1[0] = new E(0, Integer.parseInt(st.nextToken()));
    for (int i = 1; i < n; i++) {
      arr1[i] = new E(i, arr1[i - 1].sum + Integer.parseInt(st.nextToken()));
    }

    m = Integer.parseInt(br.readLine());
    arr2 = new E[m];
    st = new StringTokenizer(br.readLine());
    arr2[0] = new E(0, Integer.parseInt(st.nextToken()));
    for (int i = 1; i < m; i++) {
      arr2[i] = new E(i, arr2[i - 1].sum + Integer.parseInt(st.nextToken()));
    }

    for (int i = 0; i <= T; i++) {
      int j = T - i;

      cnt += (Search(arr1, i, n) * Search(arr2, j, m));
    }

    System.out.println(cnt);
  }

  private static int Search(E[] arr, int target, int size) {
    int ret = 0;

    for (int p2 = 0; p2 < size; p2++) {
      int v = arr[p2].sum - target;
      // 이진 탐색
      int start = 0;
      int end = n - 1;
      int mid = 0;

      while (start <= end) {
        mid = (start + end) / 2;
        int v_mid = arr[mid].sum;

        if (v_mid == v) {
          break;
        }

        if (v < v_mid) {
          end = mid - 1;
        } else {
          start = mid + 1;
        }
      }

      start = mid;
      end = mid;
      while (start - 1 >= 0 && arr[start].sum == v) {
        start--;
      }
      while (end + 1 < size && arr[end].sum == v) {
        end++;
      }

      for (int i = start; i <= end; i++) {
        if (i < p2) {
          ret++;
        }
      }
    }

    return ret;
  }
}
