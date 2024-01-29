package baekjoon;

import java.io.*;
import java.util.*;

public class prob4963 {
    static int w;
    static int h;
    static int[][] square;
    static int[] d_row = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
    static int[] d_col = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };

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

        while (true) {
            int cnt = 0;
            Queue<xy> q = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) {
                break;
            }

            square = new int[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    square[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (square[i][j] == 1) {
                        cnt++;
                        q.add(new xy(i, j));
                        square[i][j] = 0;
                    }

                    while (!q.isEmpty()) {
                        xy cur = q.poll();

                        for (int k = 0; k < 9; k++) {
                            int new_row = cur.x + d_row[k];
                            int new_col = cur.y + d_col[k];

                            if(new_row >= 0 && new_row < h && new_col >= 0 && new_col < w && square[new_row][new_col] == 1){
                                q.add(new xy(new_row, new_col));
                                square[new_row][new_col] = 0;
                            }
                        }
                    }
                }
            }
            System.out.println(cnt);
        }
    }
}
