package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob1992_2 {
    static StringBuilder result = new StringBuilder();
    static int N;
    static int[][] map;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        Compress(0, 0, N - 1, N - 1);

        System.out.println(result);
    }

    private static void Compress(int x1, int y1, int x2, int y2) {
        int target = map[x1][y1];
        boolean flag = true;
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (target != map[i][j]) {
                    flag = false;
                }
            }
        }

        if (flag) {
            result.append(target);
        } else {
            result.append("(");
            // 1사분면
            Compress(x1, y1, (x1 + x2) / 2, (y1 + y2) / 2);
            // 2사분면
            Compress(x1,  (y1 + y2) / 2 + 1, (x1 + x2) / 2, y2);
            // 3사분면
            Compress((x1 + x2) / 2 + 1, y1, x2, (y1 + y2) / 2);
            // 4사분면
            Compress((x1 + x2) / 2 + 1, (y1 + y2) / 2 + 1, x2, y2);
            result.append(")");
        }

    }

}
