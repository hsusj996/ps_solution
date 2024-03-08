package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob20058 {
    static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };

    static class xy {
        int x;
        int y;

        public xy(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static int M;
    static int size;
    static int[][] board;
    static int iceSum = 0;
    static int maxSizeOfIce = 0;
    static StringTokenizer st = null;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        size = (int) Math.pow(2, N);
        board = new int[size][size];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int level = Integer.parseInt(st.nextToken());
            int unit = (int) Math.pow(2, level);

            int[][] newBoard = new int[size][size];
            for (int row = 0; row < size; row += unit) {
                for (int col = 0; col < size; col += unit) {
                    for (int dx = 0; dx < unit; dx++) {
                        for (int dy = 0; dy < unit; dy++) {
                            newBoard[row + dy][col + unit - 1 - dx] = board[row + dx][col + dy];
                        }
                    }
                }
            }
            board = newBoard;

            UpdateIce();
        }

        FloodFill();

        System.out.println(iceSum);
        System.out.println(maxSizeOfIce);
    }

    private static void FloodFill() {
        boolean[][] visited = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0 || visited[i][j]) {
                    continue;
                }

                int iceSize = 0;
                Queue<xy> q = new ArrayDeque<>();
                q.add(new xy(i, j));
                visited[i][j] = true;
                iceSum += board[i][j];
                iceSize++;

                while (!q.isEmpty()) {
                    xy cur = q.poll();

                    for (int k = 0; k < 4; k++) {
                        int newX = cur.x + d[0][k];
                        int newY = cur.y + d[1][k];

                        if (IsOutBound(newX, newY) || visited[newX][newY] || board[newX][newY] == 0) {
                            continue;
                        }

                        q.add(new xy(newX, newY));
                        visited[newX][newY] = true;
                        iceSum += board[newX][newY];
                        iceSize++;
                    }
                }

                maxSizeOfIce = Math.max(maxSizeOfIce, iceSize);
            }
        }
    }

    private static void UpdateIce() {
        Queue<xy> updateQ = new ArrayDeque<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0) {
                    continue;
                }
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int newX = i + d[0][k];
                    int newY = j + d[1][k];

                    if (IsOutBound(newX, newY) || board[newX][newY] == 0) {
                        cnt++;
                    }
                }
                if (cnt > 1) {
                    updateQ.add(new xy(i, j));
                }
            }
        }

        updateQ.forEach(c -> board[c.x][c.y]--);
    }

    private static boolean IsOutBound(int x, int y) {
        return x < 0 || x >= size || y < 0 || y >= size;
    }

    private static void Print_arr(int[][] newBoard) {
        int size = newBoard[0].length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(newBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
}
