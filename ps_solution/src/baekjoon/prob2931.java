package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob2931 {
    static class Node {
        int x;
        int y;
        int dir;

        public Node(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

    }

    static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };
    static final int GND = 0;
    static final int PIPE_1 = 1;
    static final int PIPE_2 = 2;
    static final int PIPE_3 = 3;
    static final int PIPE_4 = 4;
    static final int PIPE_VER = 5;
    static final int PIPE_HOR = 6;
    static final int PIPE_CROSS = 7;
    static final int MOSK = 8;
    static final int ZARG = 9;
    static int N, M;
    static int[][] board;
    static int startX;
    static int startY;
    static Node ans;
    static StringTokenizer st = null;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                switch (c) {
                    case '.':
                        board[i][j] = GND;
                        break;
                    case '1':
                        board[i][j] = PIPE_1;
                        break;
                    case '2':
                        board[i][j] = PIPE_2;
                        break;
                    case '3':
                        board[i][j] = PIPE_3;
                        break;
                    case '4':
                        board[i][j] = PIPE_4;
                        break;
                    case '|':
                        board[i][j] = PIPE_VER;
                        break;
                    case '-':
                        board[i][j] = PIPE_HOR;
                        break;
                    case '+':
                        board[i][j] = PIPE_CROSS;
                        break;
                    case 'M':
                        board[i][j] = MOSK;
                        startX = i;
                        startY = j;
                        break;
                    case 'Z':
                        board[i][j] = ZARG;
                        break;
                    default:
                        break;
                }
            }
        }

        bfs();
        ans.x++;
        ans.y++;
        System.out.println(ans.x + " " + ans.y + " " + intToDir(ans.dir));
    }

    private static char intToDir(int dir) {
        switch (dir) {
            case 1:
                return '1';
            case 2:
                return '2';
            case 3:
                return '3';
            case 4:
                return '4';
            case 5:
                return '|';
            case 6:
                return '-';
            case 7:
                return '+';
            default:
                return '.';
        }
    }

    private static void bfs() {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        q.add(new Node(startX, startY, PIPE_HOR));
        q.add(new Node(startX, startY, PIPE_VER));
        visited[startX][startY] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                if (!IsRightDir(cur.dir, i)) {
                    continue;
                }
                int nx = cur.x + d[0][i];
                int ny = cur.y + d[1][i];

                if (IsOutBound(nx, ny) || visited[nx][ny]) {
                    continue;
                }

                if (board[nx][ny] == GND) {
                    if (IsEmptyPipe(nx, ny, i)) {
                        return;
                    } else {
                        continue;
                    }
                }

                visited[nx][ny] = true;
                q.add(new Node(nx, ny, board[nx][ny]));
            }
        }
    }

    private static boolean IsEmptyPipe(int x, int y, int dir) {
        boolean[] pipe = new boolean[4];
        for (int i = 0; i < 4; i++) {
            int nx = x + d[0][i];
            int ny = y + d[1][i];

            if (IsOutBound(nx, ny)) {
                continue;
            }

            if (board[nx][ny] != GND && IsRightDir(board[nx][ny], (i + 2) % 4)) {
                pipe[i] = true;
            }
        }

        if (pipe[0] && pipe[2]) {
            ans = new Node(x, y, PIPE_VER);
            return true;
        }
        if (pipe[1] && pipe[3]) {
            ans = new Node(x, y, PIPE_HOR);
            return true;
        }
        if (pipe[0] && pipe[1] && pipe[2] && pipe[3]) {
            ans = new Node(x, y, PIPE_CROSS);
            return true;
        }
        if (pipe[1] && pipe[2]) {
            ans = new Node(x, y, PIPE_1);
            return true;
        }
        if (pipe[0] && pipe[1]) {
            ans = new Node(x, y, PIPE_2);
            return true;
        }
        if (pipe[0] && pipe[3]) {
            ans = new Node(x, y, PIPE_3);
            return true;
        }
        if (pipe[2] && pipe[3]) {
            ans = new Node(x, y, PIPE_4);
            return true;
        }

        return false;
    }

    private static boolean IsRightDir(int prev, int now) {
        switch (now) {
            case 0:
                if (prev == PIPE_VER || prev == PIPE_CROSS || prev == PIPE_2 || prev == PIPE_3) {
                    return true;
                }
                break;
            case 1:
                if (prev == PIPE_HOR || prev == PIPE_CROSS || prev == PIPE_1 || prev == PIPE_2) {
                    return true;
                }
                break;
            case 2:
                if (prev == PIPE_VER || prev == PIPE_CROSS || prev == PIPE_1 || prev == PIPE_4) {
                    return true;
                }
                break;
            case 3:
                if (prev == PIPE_HOR || prev == PIPE_CROSS || prev == PIPE_3 || prev == PIPE_4) {
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }

    private static boolean IsOutBound(int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= N || ny >= M;
    }
}
