package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob3085 {
    static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };
    static int N;
    static char[][] board;
    static int ans = 0;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = input.charAt(j);
            }
        }

        // 완전 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    int newX = i + d[0][k];
                    int newY = j + d[1][k];

                    if (IsOutBound(newX, newY) || board[newX][newY] == board[i][j]) {
                        continue;
                    }

                    // swap
                    swap(i, j, newX, newY);

                    for (int d = 0; d < N; d++) {
                        checkRow(d);
                        checkCol(d);
                    }

                    // swap
                    swap(i, j, newX, newY);
                }
            }
        }

        System.out.println(ans);
    }

    private static void checkRow(int row) {
        // check Row
        char prev = 'A';
        int curLength = 1;
        for (int i = 0; i < N; i++) {
            if (board[row][i] == prev) {
                curLength++;
            } else {
                ans = Math.max(ans, curLength);
                prev = board[row][i];
                curLength = 1;
            }
        }
        ans = Math.max(ans, curLength);
    }

    private static void checkCol(int col) {
        char prev = 'A';
        int curLength = 1;
        for (int i = 0; i < N; i++) {
            if (board[i][col] == prev) {
                curLength++;
            } else {
                ans = Math.max(ans, curLength);
                prev = board[i][col];
                curLength = 1;
            }
        }
        ans = Math.max(ans, curLength);
    }

    private static void swap(int x1, int y1, int x2, int y2) {
        char tmp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = tmp;
    }

    private static boolean IsOutBound(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}
