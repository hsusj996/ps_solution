package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob1194_2 {
    static final char GROUND = '.';
    static final char WALL = '#';
    static final char START = '0';
    static final char DEST = '1';
    static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };

    static class Minsik {
        int x;
        int y;
        int keys;
        int depth;

        public Minsik(int x, int y, int keys, int depth) {
            this.x = x;
            this.y = y;
            this.keys = keys;
            this.depth = depth;
        }

    }

    static StringTokenizer st = null;
    static int N, M;
    static char[][] board;
    static boolean[][][] visited;
    static int[] start = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        visited = new boolean[64][N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = input.charAt(j);
                if (board[i][j] == START) {
                    start[0] = i;
                    start[1] = j;
                    board[i][j] = GROUND;
                }
            }
        }

        // BFS 수행
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Minsik> q = new ArrayDeque<>();
        q.add(new Minsik(start[0], start[1], 0, 0));
        visited[0][start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            Minsik cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int newX = cur.x + d[0][i];
                int newY = cur.y + d[1][i];

                if (newX < 0 || newX >= N || newY < 0 || newY >= M) {
                    continue;
                }
                if (visited[cur.keys][newX][newY])
                    continue;
                if (board[newX][newY] == WALL)
                    continue;
                if (board[newX][newY] == DEST) {
                    return cur.depth + 1;
                }
                if (board[newX][newY] == GROUND) {
                    q.add(new Minsik(newX, newY, cur.keys, cur.depth + 1));
                    visited[cur.keys][newX][newY] = true;
                    continue;
                }
                if (board[newX][newY] >= 'a' && board[newX][newY] <= 'f') {
                    int keybit = board[newX][newY] - 'a';
                    int newkeys = cur.keys | (1 << keybit);
                    q.add(new Minsik(newX, newY, newkeys, cur.depth + 1));
                    visited[newkeys][newX][newY] = true;
                    continue;
                }
                if(board[newX][newY] >= 'A' && board[newX][newY] <= 'F'){
                    int keybit = board[newX][newY] - 'A';
                    if((cur.keys & (1 << keybit)) == (1 << keybit)){
                        q.add(new Minsik(newX, newY, cur.keys, cur.depth + 1));
                        visited[cur.keys][newX][newY] = true;
                    }
                }
            }
        }

        return -1;
    }
}
