package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea1953 {
    static final boolean[][] pipe = {
            { false, false, false, false },
            { true, true, true, true },
            { true, false, true, false },
            { false, true, false, true },
            { true, true, false, false },
            { false, true, true, false },
            { false, false, true, true },
            { true, false, false, true }
    };
    static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };

    static class xy {
        int x;
        int y;
        int depth;

        public xy(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }

    }

    static StringBuilder result = new StringBuilder();
    static StringTokenizer st = null;
    static int T, N, M, R, C, L;
    static int[][] board;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            result.append("#").append(test_case).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            board = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int cnt = BFS();

            result.append(cnt).append("\n");
        }
        System.out.println(result);
    }

    private static int BFS() {
        int ret = 0;
        boolean[][] visited = new boolean[N][M];
        Queue<xy> q = new ArrayDeque<>();

        q.add(new xy(R, C, 1));
        visited[R][C] = true;

        while (!q.isEmpty()) {
            xy cur = q.poll();

            if (cur.depth > L) {
                continue;
            }

            ret++;

            for (int i = 0; i < 4; i++) {
                int newX = cur.x + d[0][i];
                int newY = cur.y + d[1][i];

                if (!IsPossible(cur.x, cur.y, newX, newY, i) || visited[newX][newY]) {
                    continue;
                }

                q.add(new xy(newX, newY, cur.depth + 1));
                visited[newX][newY] = true;
            }
        }

        return ret;
    }

    private static boolean IsPossible(int x, int y, int newX, int newY, int direction) {
        if (newX < 0 || newX >= N || newY < 0 || newY >= M) {
            return false;
        }

        if (pipe[board[x][y]][direction] && pipe[board[newX][newY]][(direction + 2) % 4]) {
            return true;
        }

        return false;
    }
}
