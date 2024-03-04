package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob1194 {
    static class Node {
        int x;
        int y;
        int depth;
        int keys;

        public Node(int x, int y, int depth, int keys) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.keys = keys;
        }

    }

    static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };
    static StringTokenizer st = null;
    static char[][] board;
    static int N, M;
    static Node minsik;

    public static void main(String[] args) throws IOException {
        int a = (32 >> 5 & 1);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] == '0') {
                    minsik = new Node(i, j, 0, 0);
                    board[i][j] = '.';
                }
            }
        }

        int result = BFS(minsik);
        System.out.println(result);
    }

    private static int BFS(Node start) {
        Queue<Node> q = new ArrayDeque<>();
        Queue<Node> nextQ = new ArrayDeque<>();

        boolean[][] visited = new boolean[N][M];
        q.add(start);
        visited[start.x][start.y] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + d[0][i];
                int nextY = cur.y + d[1][i];

                if (IsOutBound(nextX, nextY) || visited[nextX][nextY] || board[nextX][nextY] == '#') {
                    continue;
                }

                if (board[nextX][nextY] == '1') {
                    return cur.depth + 1;
                }

                if (board[nextX][nextY] == '.') {
                    q.add(new Node(nextX, nextY, cur.depth + 1, cur.keys));
                    visited[nextX][nextY] = true;
                    continue;
                }

                if (board[nextX][nextY] >= 65 && board[nextX][nextY] <= 70) {
                    if ((cur.keys >> (board[nextX][nextY] - 'A') & 1) == 1) {
                        q.add(new Node(nextX, nextY, cur.depth + 1, cur.keys));
                    }
                    visited[nextX][nextY] = true;
                    continue;
                }

                if (board[nextX][nextY] >= 97 && board[nextX][nextY] <= 102) {
                    nextQ.add(new Node(nextX, nextY, cur.depth + 1, cur.keys | (1 << board[nextX][nextY] - 'a')));
                    continue;
                }
            }
        }

        int ret = Integer.MAX_VALUE;
        while (!nextQ.isEmpty()) {
            ret = Math.min(ret, BFS(nextQ.poll()));
        }

        if (ret == Integer.MAX_VALUE) {
            return -1;
        }
        return ret;
    }

    private static boolean IsOutBound(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }
}
