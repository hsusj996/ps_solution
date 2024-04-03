package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob27172 {
  static int N;
  static boolean[] checkBoard;
  static int[] scoreBoard;
  static int[] player;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    player = new int[N];
    checkBoard = new boolean[1000001];
    scoreBoard = new int[1000001];

    StringTokenizer st = new StringTokenizer(br.readLine());
    int max = -1;
    for (int i = 0; i < N; i++) {
      int n = Integer.parseInt(st.nextToken());
      player[i] = n;
      checkBoard[n] = true;
      max = Math.max(max, n);
    }

    for (int i = 0; i < N; i++) {
      int n = player[i];
      if (!checkBoard[n])
        continue;

      int c = n * 2;
      for (; c <= max; c += n) {
        if (checkBoard[c]) {
          scoreBoard[n]++;
          scoreBoard[c]--;
        }
      }
    }

    for (int i = 0; i < N; i++) {
      System.out.print(scoreBoard[player[i]] + " ");
    }
  }
}
