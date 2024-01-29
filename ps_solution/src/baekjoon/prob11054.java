package baekjoon;

import java.util.*;
import java.io.*;

public class prob11054 {
  static int N;
  static int[] arr;
  static int[] dp;
  static int[] dp_asc;
  static int[] dp_desc;
  static int max = Integer.MIN_VALUE;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    arr = new int[N];
    dp = new int[N];
    dp_asc = new int[N];
    dp_desc = new int[N];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    ascending();
    descending();

    for (int i = 0; i < N; i++) {
      dp[i] = dp_asc[i] + dp_desc[i] - 1;
      max = Math.max(max, dp[i]);
    }

    System.out.println(max);
  }

  static void ascending() {
    for (int i = 0; i < N; i++) {
      dp_asc[i] = 1;
      for (int j = 0; j < i; j++) {
        if (arr[j] < arr[i] && dp_asc[i] < dp_asc[j] + 1) {
          dp_asc[i] = dp_asc[j] + 1;
        }
      }
    }
  }

  static void descending() {
    for (int i = N - 1; i >= 0; i--) {
      dp_desc[i] = 1;
      for (int j = i + 1; j < N; j++) {
        if (arr[j] < arr[i] && dp_desc[i] < dp_desc[j] + 1) {
          dp_desc[i] = dp_desc[j] + 1;
        }
      }
    }
  }
}
