package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class prob16918 {
    static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };

    static class xy {
        int x;
        int y;

        public xy(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    static StringTokenizer st = null;
    static int R, C, N;
    static char[][] board;
    static char[][] board2;
    static char[][] board3;
    static List<xy> bombList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        board2 = new char[R][C];
        board3 = new char[R][C];

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = input.charAt(j);
                if (board[i][j] == 'O') {
                    bombList.add(new xy(i, j));
                }
            }
        }

        for (int i = 0; i < R; i++) {
            Arrays.fill(board2[i], 'O');
            Arrays.fill(board3[i], 'O');
        }

        for (xy bomb : bombList) {
            board2[bomb.x][bomb.y] = '.';
            for (int i = 0; i < 4; i++) {
                int newX = bomb.x + d[0][i];
                int newY = bomb.y + d[1][i];

                if (newX < 0 || newX >= R || newY < 0 || newY >= C) {
                    continue;
                }

                board2[newX][newY] = '.';
            }
        }

        bombList.clear();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board2[i][j] == 'O') {
                    bombList.add(new xy(i, j));
                }
            }
        }

        for (xy bomb : bombList) {
            board3[bomb.x][bomb.y] = '.';
            for (int i = 0; i < 4; i++) {
                int newX = bomb.x + d[0][i];
                int newY = bomb.y + d[1][i];

                if (newX < 0 || newX >= R || newY < 0 || newY >= C) {
                    continue;
                }

                board3[newX][newY] = '.';
            }
        }

        StringBuilder sb = new StringBuilder();
        if (N % 2 == 0) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    sb.append("O");
                }
                sb.append("\n");
            }
        } else if (N == 1) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    sb.append(board[i][j]);
                }
                sb.append("\n");
            }
        } else {
            if (N % 4 == 1) {
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        sb.append(board3[i][j]);
                    }
                    sb.append("\n");
                }
            } else if (N % 4 == 3) {
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        sb.append(board2[i][j]);
                    }
                    sb.append("\n");
                }
            }
        }

        System.out.println(sb.toString());
    }
}
