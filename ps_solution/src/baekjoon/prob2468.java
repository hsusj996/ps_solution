package baekjoon;

import java.io.*;
import java.util.*;

public class prob2468 {
    static int N;
    static int[][] square;
    static int max = Integer.MIN_VALUE;
    static int ans;
    static int[] d_row = { -1, 0, 1, 0 };
    static int[] d_col = { 0, -1, 0, 1 };

    static class xy {
        int x;
        int y;

        public xy(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        square = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                square[i][j] = Integer.parseInt(st.nextToken());

                if (max < square[i][j]) {
                    max = square[i][j];
                }
            }
        }

        for (int height = 0; height < max; height++) {
            boolean[][] safe = new boolean[N][N];
            Queue<xy> q = new LinkedList<>();
            int cnt = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (square[i][j] > height) {
                        safe[i][j] = true;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (safe[i][j]) {
                        q.add(new xy(i, j));
                        safe[i][j] = false;

                        cnt++;

                        while (!q.isEmpty()) {
                            xy coordinate = q.poll();

                            for (int k = 0; k < 4; k++) {
                                int new_x = coordinate.x + d_row[k];
                                int new_y = coordinate.y + d_col[k];

                                if (new_x >= 0 && new_x < N && new_y >= 0 && new_y < N && safe[new_x][new_y]) {
                                    q.add(new xy(new_x, new_y));
                                    safe[new_x][new_y] = false;
                                }
                            }
                        }
                    }
                }
            }

            if (ans < cnt) {
                ans = cnt;
            }
        }

        System.out.println(ans);
    }
}
