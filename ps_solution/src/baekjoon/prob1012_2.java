package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob1012_2 {
    static boolean map[][];
    static int N = 0;
    static int M = 0;
    static int K = 0;
    static int cnt = 0;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            cnt = 0;
            String s1[] = br.readLine().split(" ");

            N = Integer.parseInt(s1[0]);
            M = Integer.parseInt(s1[1]);
            K = Integer.parseInt(s1[2]);

            map = new boolean[N][M];

            for (int i = 0; i < K; i++) {
                String s2[] = br.readLine().split(" ");

                map[Integer.parseInt(s2[0])][Integer.parseInt(s2[1])] = true;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j]) {
                        cnt++;
                        deleteNearbyCabbage(i, j);
                    }
                }
            }
            System.out.println(cnt);
        }
    }

    public static void deleteNearbyCabbage(int row, int col) {
        map[row][col] = false;
        for (int i = 0; i < 4; i++) {
            int nr = row + dr[i];
            int nc = col + dc[i];

            if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
                if(map[nr][nc]){
                    deleteNearbyCabbage(nr, nc);
                }
            }
        }

    }

}
