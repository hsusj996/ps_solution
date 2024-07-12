package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class prob1981 {
    static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };

    static class Element implements Comparable<Element> {
        int x;
        int y;
        int min;
        int max;

        public Element(int x, int y, int min, int max) {
            this.x = x;
            this.y = y;
            this.min = min;
            this.max = max;
        }

        @Override
        public int compareTo(Element o) {
            return (this.max - this.min) - (o.max - o.min);
        }

    }

    static int N;
    static int[][] board;
    static boolean[][][] visited;
    static StringTokenizer st = null;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        visited = new boolean[201][N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();
        for (int i = 0; i <= 200; i++) {
            if (visited[i][N - 1][N - 1]) {
                System.out.println(i);
                break;
            }
        }
    }

    private static void bfs() {
        PriorityQueue<Element> pq = new PriorityQueue<>();

        int min = Math.min(board[0][0], board[N-1][N-1]);
        int max = Math.max(board[0][0], board[N-1][N-1]);

        pq.add(new Element(min, max, board[0][0], board[0][0]));
        visited[0][0][0] = true;

        while (!pq.isEmpty()) {
            Element cur = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + d[0][i];
                int ny = cur.y + d[1][i];

                Element nxt = getNxtElement(nx, ny, cur);
                if (nxt == null) {
                    continue;
                }

                pq.add(nxt);
                visited[nxt.max - nxt.min][nxt.x][nxt.y] = true;
            }
        }
    }

    private static Element getNxtElement(int nx, int ny, Element cur) {
        if (IsOutBound(nx, ny)) {
            return null;
        }

        Element ret = new Element(nx, ny, cur.min, cur.max);
        ret.max = Math.max(ret.max, board[nx][ny]);
        ret.min = Math.min(ret.min, board[nx][ny]);

        if (visited[ret.max - ret.min][nx][ny]) {
            return null;
        }

        return ret;
    }

    private static boolean IsOutBound(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= N;
    }
}