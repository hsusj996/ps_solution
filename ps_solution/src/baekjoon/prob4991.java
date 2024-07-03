package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class prob4991 {
    static final int[][] d = { { -1, 0, 1, 0 }, { 0, 1, 0, -1 } };
    static final int GND = 0;
    static final int WALL = 1;
    static final int NODE = 2;
    static StringTokenizer st = null;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[][] board;
    static List<xy> points;
    static int[][] graph;
    static xy cleaner;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            // 초기화
            board = new int[N][M];
            points = new ArrayList<>();

            // 입력
            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                for (int j = 0; j < M; j++) {
                    char c = s.charAt(j);
                    switch (c) {

                        case '.':
                            board[i][j] = GND;
                            break;
                        case 'x':
                            board[i][j] = WALL;
                            break;
                        case 'o':
                            cleaner = new xy(i, j);
                        case '*':
                            board[i][j] = NODE;
                            points.add(new xy(i, j));
                            break;
                    }
                }
            }

            // 각 점에서 BFS 돌려서 완전 그래프 만들기
            graph = new int[points.size()][points.size()];
            boolean flag = true;

            // 각 점에서 BFS 돌리기
            for (int i = 0; i < points.size(); i++) {
                xy cur = points.get(i);
                BFS(cur, i);
                for (int j = 0; j < points.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    if (graph[i][j] == 0) {
                        flag = false;
                        break;
                    }
                }

                if (!flag) {
                    break;
                }
            }

            if (!flag) {
                sb.append(-1).append("\n");
                continue;
            }

            // 완전 그래프로 MST 만들기
            PriorityQueue<Edge> edgeQueue = new PriorityQueue<>();
            for (int i = 0; i < points.size(); i++) {
                for (int j = 0; j < points.size(); j++) {
                    if (i == j) {
                        continue;
                    }

                    edgeQueue.add(new Edge(i, j, graph[i][j]));
                }
            }

            // MST 출력
            sb.append(MST(edgeQueue)).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int MST(PriorityQueue<Edge> edgeQueue) {
        int ret = 0;
        parents = new int[points.size()];
        MakeSet();

        int n = 0;
        while (!edgeQueue.isEmpty()) {
            Edge cur = edgeQueue.poll();

            if (Union(cur.node1, cur.node2)) {
                ret += cur.dist;
                n++;
            }

            if (n >= 9) {
                break;
            }
        }

        return ret;
    }

    private static boolean Union(int node1, int node2) {
        int aRoot = FindSet(node1);
        int bRoot = FindSet(node2);

        if (aRoot == bRoot) {
            return false;
        }

        parents[bRoot] = parents[aRoot];
        return true;
    }

    private static void MakeSet() {
        for (int i = 0; i < points.size(); i++) {
            parents[i] = i;
        }
    }

    private static int FindSet(int a) {
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = FindSet(parents[a]);
    }

    private static void BFS(xy start, int idx) {
        boolean[][] visited = new boolean[N][M];
        Queue<xy> q = new ArrayDeque<>();
        q.add(start);
        visited[start.x][start.y] = true;
        int dist = 0;

        while (true) {
            int qsize = q.size();
            if (qsize == 0) {
                break;
            }

            dist++;
            while (qsize-- > 0) {
                xy cur = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + d[0][i];
                    int ny = cur.y + d[1][i];

                    if (IsOutBound(nx, ny) || visited[nx][ny] || board[nx][ny] == WALL) {
                        continue;
                    }

                    xy next = new xy(nx, ny);
                    if (board[nx][ny] == NODE) {
                        // 무슨 점인지 찾기
                        for (int j = 0; j < points.size(); j++) {
                            if (points.get(j).equals(next)) {
                                graph[idx][j] = dist;
                            }
                        }
                    }

                    visited[nx][ny] = true;
                    q.add(next);
                }
            }
        }

    }

    private static boolean IsOutBound(int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= N || ny >= M;
    }

    static class Edge implements Comparable<Edge> {
        int node1;
        int node2;
        int dist;

        public Edge(int node1, int node2, int dist) {
            this.node1 = node1;
            this.node2 = node2;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }

    }

    static class xy {
        int x;
        int y;

        public xy(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof xy)) {
                return false;
            }

            xy o = (xy) obj;
            return this.x == o.x && this.y == o.y;
        }

    }
}
