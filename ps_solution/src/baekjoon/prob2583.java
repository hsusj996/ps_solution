package baekjoon;

import java.util.*;
import java.io.*;

public class prob2583 {
    static int M;
    static int N;
    static int K;
    static int[] d_row = { 0, -1, 0, 1 };
    static int[] d_col = { -1, 0, 1, 0 };
    static boolean[][] square;
    static ArrayList<Integer> area_arr = new ArrayList<>();

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

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        square = new boolean[M][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            for (int j = x1; j < x2; j++) {
                for (int k = y1; k < y2; k++) {
                    square[j][k] = true;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (square[i][j]) {
                    continue;
                }
                int area = 0;
                Queue<xy> q = new LinkedList<>();
                q.add(new xy(i, j));
                area++;
                square[i][j] = true;

                while (!q.isEmpty()) {
                    xy now = q.poll();

                    for (int k = 0; k < 4; k++) {
                        int new_x = now.x + d_row[k];
                        int new_y = now.y + d_col[k];

                        if (new_x >= 0 && new_x < M && new_y >= 0 && new_y < N && !square[new_x][new_y]) {
                            area++;
                            q.add(new xy(new_x, new_y));
                            square[new_x][new_y] = true;
                        }
                    }
                }
                area_arr.add(area);
            }
        }

        Collections.sort(area_arr);
        System.out.println(area_arr.size());
        for (int i = 0; i < area_arr.size(); i++) {
            System.out.print(area_arr.get(i) + " ");
        }
    }
}
