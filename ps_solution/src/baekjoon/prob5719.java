package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class prob5719 {
    static final int INF = 100_000_000;

    static class Node implements Comparable<Node> {
        int to;
        int distance;

        public Node(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }

    }

    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st = null;
    static int[][] graph;
    static int[] minDistance;
    static List<Integer>[] prevList;
    static int N, M, S, D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) {
                break;
            }
            graph = new int[N][N];
            minDistance = new int[N];
            prevList = new ArrayList[N];
            for(int i=0;i<N;i++){
                prevList[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                graph[from][to] = d;
            }

            dijkstra();
            
            dfs(D);

            dijkstra();

            sb.append(minDistance[D] >= INF ? -1 : minDistance[D]).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void dfs(int n) {
        for(int c : prevList[n]){
            if(graph[c][n] == 0){
                continue;
            }
            graph[c][n] = 0;
            dfs(c);
        }
    }

    private static void dijkstra() {
        Arrays.fill(minDistance, INF);
        boolean[] visited = new boolean[N];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(S, 0));
        minDistance[S] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.to]) {
                continue;
            }
            visited[cur.to] = true;

            for (int i = 0; i < N; i++) {
                if (graph[cur.to][i] == 0) {
                    continue;
                }

                int distance = minDistance[cur.to] + graph[cur.to][i];
                if (minDistance[i] > distance) {
                    prevList[i].clear();
                    prevList[i].add(cur.to);
                    minDistance[i] = distance;
                    pq.add(new Node(i, distance));
                } else if (minDistance[i] == distance) {
                    prevList[i].add(cur.to);
                }
            }
        }

    }
}
