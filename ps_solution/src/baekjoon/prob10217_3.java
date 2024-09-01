package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class prob10217_3 {
    static final int INF = 100_000_000;

    static class Edge{
        int to;
        int cost;
        int time;

        public Edge(int to, int cost, int time) {
            this.to = to;
            this.cost = cost;
            this.time = time;
        }
    }

    static StringTokenizer st = null;
    static StringBuilder sb = new StringBuilder();
    static int N, M, K;
    static List<Edge>[] graph;
    static int[][] minTime;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }
            minTime = new int[M + 1][N + 1];
            for (int i = 0; i <= M; i++) {
                Arrays.fill(minTime[i], INF);
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                graph[u].add(new Edge(v, c, d));
            }

            minTime[0][1] = 0;
            for (int i = 0; i <= M; i++) {
                for (int j = 1; j <= N; i++) {
                    for (Edge e : graph[j]) {
                        if (i + e.cost > M) {
                            continue;
                        }

                        minTime[i + e.cost][e.to] = Math.min(minTime[i + e.cost][e.to], minTime[i][j] + e.time);
                    }
                }
            }

            int min = INF;
            for (int i = 1; i <= M; i++) {
                min = Math.min(min, minTime[i][N]);
            }
            sb.append(min == INF ? "Poor KCM" : min).append("\n");
        }
        System.out.println(sb.toString());
    }
}