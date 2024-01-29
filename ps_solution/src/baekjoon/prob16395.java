package baekjoon;

import java.util.*;
import java.io.*;

public class prob16395 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    int[][] pascal = new int[n][n];
    pascal[0][0] = 1;

    for (int i = 1; i < n; i++) {

      for (int j = 0; j <= i; j++) {
        if (j == 0 || j == i) {
          pascal[i][j] = 1;
        } else {
          pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
        }
      }
    }

    System.out.println(pascal[n - 1][k - 1]);
  }
}
