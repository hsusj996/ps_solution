package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob17070 {
    static int N;
    static int[][] map;

    static class pipe {
        int row;
        int col;
        int state;

        public pipe(int row, int col, int state) {
            this.row = row;
            this.col = col;
            this.state = state;
        }
    }

    static int[][] d_row = { { 0, 0, 1 }, { 0, 1, 1 }, { 0, 1, 1 } };
    static int[][] d_col = { { 1, 0, 1 }, { 0, 0, 1 }, { 1, 0, 1 } };
    static int cnt = 0;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        pipe initPipe = new pipe(1, 2, 0);
        DFS(initPipe, 0);

        System.out.println(cnt);
    }

    static void DFS(pipe now, int depth) {
        if (now.row == N && now.col == N) {
            cnt++;
            return;
        }

        for (int i = 0; i < 3; i++) {
            if ((now.state == 0 && i == 1) || now.state == 1 && i == 0) {
                continue;
            }
            int new_row = now.row + d_row[now.state][i];
            int new_col = now.col + d_col[now.state][i];

            if (isOutBound(new_row, new_col)) {
                continue;
            }

            if (IsTouchWall(new_row, new_col, i)) {
                continue;
            }

            DFS(new pipe(new_row, new_col, i), depth + 1);
        }
    }

    static boolean isOutBound(int row, int col) {
        return row <= 0 || row > N || col <= 0 || col > N;
    }

    static boolean IsTouchWall(int row, int col, int state) {
        switch (state) {
            case 0:
            case 1:
                return map[row][col] == 1;
            case 2:
                if (map[row - 1][col] == 1 || map[row][col] == 1 || map[row][col - 1] == 1) {
                    return true;
                }
            default:
                break;
        }

        return false;
    }
}
