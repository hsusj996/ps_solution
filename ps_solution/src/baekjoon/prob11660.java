package baekjoon;

import java.util.*;
import java.io.*;

public class prob11660 {
    static int N;
    static int M;
    static int x1, x2, y1, y2;
    static int[][] square;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        square = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int row_sum = 0;

            for (int j = 1; j <= N; j++) {
                row_sum += Integer.parseInt(st.nextToken());
                square[i][j] = square[i - 1][j] + row_sum;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            int ans = square[x2][y2] - square[x2][y1 - 1] - square[x1 - 1][y2] + square[x1 - 1][y1 - 1];

            System.out.println(ans);
        }
    }
}
