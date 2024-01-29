package baekjoon;

import java.util.*;
import java.io.*;

public class prob2448 {
  static int N;
  static char[][] stars;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());

    stars = new char[N][2 * N - 1];
    for (int i = 0; i < N; i++) {
      Arrays.fill(stars[i], ' ');
    }

    draw_star(0, N - 1, N);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < 2 * N - 1; j++) {
        sb.append(stars[i][j]);
      }
      sb.append('\n');
    }

    System.out.println(sb.toString());
  }

  static void draw_star(int row, int col, int N) {
    if (N == 3) {
      stars[row][col] = '*';
      stars[row + 1][col - 1] = stars[row + 1][col + 1] = '*';
      stars[row + 2][col - 2] = stars[row + 2][col
          - 1] = stars[row + 2][col] = stars[row + 2][col + 1] = stars[row + 2][col + 2] = '*';
    } else {
      int bias = N / 2;
      draw_star(row, col, bias);
      draw_star(row + bias, col - bias, bias);
      draw_star(row + bias, col + bias, bias);
    }
  }
}
