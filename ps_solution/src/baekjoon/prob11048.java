package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob11048 {
    static boolean flag = true;
    static int N, M;
    static int[][] map;
    static int[][] dpMap;
    static boolean[][] visited;

    static class cdnt {
        int x;
        int y;

        cdnt(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] d_row = { 0, 1, 1 };
    static int[] d_col = { 1, 0, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dpMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                dpMap[i][j] = map[i][j] = Integer.parseInt(st.nextToken());

            }
        }

        while (flag) {
            flag = false;
            BFS();
        }

        System.out.println(dpMap[N - 1][M - 1]);
    }

    private static void BFS() {
        Queue<cdnt> q = new LinkedList<>();
        visited = new boolean[N][M];
        q.add(new cdnt(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            cdnt now = q.poll();
            int max = 0;
            for (int i = 0; i < 3; i++) {
                int new_row = now.x - d_row[i];
                int new_col = now.y - d_col[i];

                if (IsOutBound(new_row, new_col)) {
                    continue;
                }

                max = Math.max(max, dpMap[new_row][new_col]);
            }

            if (max + map[now.x][now.y] > dpMap[now.x][now.y]) {
                flag = true;
                dpMap[now.x][now.y] = max + map[now.x][now.y];
            }

            for (int i = 0; i < 3; i++) {
                int new_row = now.x + d_row[i];
                int new_col = now.y + d_col[i];

                if (IsOutBound(new_row, new_col) || visited[new_row][new_col]) {
                    continue;
                }

                q.add(new cdnt(new_row, new_col));
                visited[new_row][new_col] = true;
            }
        }
    }

    private static boolean IsOutBound(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }
}
