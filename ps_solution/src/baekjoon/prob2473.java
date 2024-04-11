package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class prob2473 {
  static StringTokenizer st = null;
  static long sumNear0 = Long.MAX_VALUE;
  static List<Integer> bestCase = new ArrayList<>();
  static long[] solArr;
  static int N;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());

    solArr = new long[N];
    for (int i = 0; i < N; i++) {
      solArr[i] = Long.parseLong(st.nextToken());
    }
    Arrays.sort(solArr);

    for (int i = 0; i < N - 2; i++) {
      for (int j = i + 1; j < N - 1; j++) {
        int k = binarySearch(i, j);
        long sum = solArr[i] + solArr[j] + solArr[k];
        if (Math.abs(sum - 0) < Math.abs(sumNear0 - 0)) {
          sumNear0 = sum;
          bestCase.clear();
          bestCase.add(i);
          bestCase.add(j);
          bestCase.add(k);
        }
      }
    }

    Collections.sort(bestCase);
    bestCase.forEach(o -> System.out.print(solArr[o] + " "));
  }

  private static int binarySearch(int i, int j) {
    long target = (-1) * (solArr[i] + solArr[j]);

    int left = j + 1;
    int right = N - 1;
    int mid = 0;
    while (left <= right) {
      mid = (left + right) / 2;

      if (target == solArr[mid]) {
        return mid;
      }

      if (target < solArr[mid]) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    if (right == j) {
      return j + 1;
    } else if (left == N) {
      return N - 1;
    } else {
      long prevNum = solArr[left];
      long nextNum = solArr[right];

      long v1 = Math.abs(prevNum - target);
      long v2 = Math.abs(nextNum - target);

      if (v1 <= v2) {
        return left;
      } else {
        return right;
      }
    }
  }
}
