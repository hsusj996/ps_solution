package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob1644 {
  static final int MAX = 4_000_000;
  static boolean[] primaryBoard;
  static int N;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    primaryBoard = new boolean[MAX + 1];
    // 에라토스테네스의 체
    for (int i = 2; i <= MAX; i++) {
      if (primaryBoard[i]) {
        continue;
      }
      for (int j = i * 2; j <= MAX; j += i) {
        primaryBoard[j] = true;
      }
    }

    // 투포인터로 합 구하기
    int p1 = 2;
    int p2 = 2;

    int sum = 2;
    int cnt = 0;
    while (p1 <= p2 && p2 <= N) {
      if (sum == N) {
        cnt++;

        sum -= p1;
        p1 = NextPrimary(p1);
        p2 = NextPrimary(p2);
        sum += p2;

        continue;
      }

      if (sum < N) {
        p2 = NextPrimary(p2);
        sum += p2;
        continue;
      }

      if (sum > N) {
        sum -= p1;
        p1 = NextPrimary(p1);
        continue;
      }
    }

    System.out.println(cnt);
  }

  private static int NextPrimary(int n) {
    for (int i = n + 1; i <= N; i++) {
      if (primaryBoard[i])
        continue;

      return i;
    }

    return N + 1;
  }
}
