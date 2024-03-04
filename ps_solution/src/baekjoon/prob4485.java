package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class prob4485 {
    static final int INF = 1_000_000;
    static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };

    static class xy {
        int x;
        int y;

        public xy(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        xy to;
        int weight;

        public Edge(xy to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static StringBuilder result = new StringBuilder();
    static StringTokenizer st = null;
    static int N;
    static int[][] board;
    static int[][] minDistance;
    static boolean[][] visited;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test_case = 0;
        while (++test_case > 0) {
            N = Integer.parseInt(br.readLine());

            if (N == 0)
                break;

            result.append("Problem ").append(test_case).append(": ");

            board = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            minDistance = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(minDistance[i], INF);
            }
            visited = new boolean[N][N];

            Dijkstra();

            result.append(minDistance[N - 1][N - 1]).append("\n");
        }

        System.out.println(result);
    }

    private static void Dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(new xy(0, 0), board[0][0]));
        minDistance[0][0] = board[0][0];

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if(visited[cur.to.x][cur.to.y]){
                continue;
            }
            visited[cur.to.x][cur.to.y] = true;

            for (int i = 0; i < 4; i++) {
                int newX = cur.to.x + d[0][i];
                int newY = cur.to.y + d[1][i];

                if (IsOutBound(newX, newY) || visited[newX][newY]) {
                    continue;
                }

                int min = minDistance[cur.to.x][cur.to.y] + board[newX][newY];
                if (minDistance[newX][newY] > min) {
                    minDistance[newX][newY] = min;
                    pq.add(new Edge(new xy(newX, newY), cur.weight + board[newX][newY]));
                }
            }
        }
    }

    private static boolean IsOutBound(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}
