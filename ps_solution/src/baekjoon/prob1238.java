package baekjoon;

import java.io.*;
import java.util.*;

public class prob1238 {
    static int N;
    static int M;
    static int X;
    static int min_distance_from_X[];
    static int min_distance_to_X[];
    static List<Vertex>[] vertex_info;
    static List<Vertex>[] r_vertex_info;
    static int ans = -1;

    static class Vertex implements Comparable<Vertex>{
        int to;
        int cost;

        public Vertex(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        vertex_info = new ArrayList[N + 1];
        r_vertex_info = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            vertex_info[i] = new ArrayList<>();
            r_vertex_info[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            vertex_info[from].add(new Vertex(to, cost));
            r_vertex_info[to].add(new Vertex(from, cost));
        }

        min_distance_from_X = dijkstra(vertex_info);
        min_distance_to_X = dijkstra(r_vertex_info);

        for (int i = 1; i < N + 1; i++) {
            int round_distance = min_distance_to_X[i] + min_distance_from_X[i];

            ans = Math.max(ans, round_distance);
        }
        System.out.println(ans);

        return;
    }

    public static int[] dijkstra(List<Vertex>[] list) {
        int[] min_distance = new int[N + 1];
        Arrays.fill(min_distance, Integer.MAX_VALUE);
        boolean[] visited = new boolean[N + 1];

        Queue<Vertex> q = new PriorityQueue<>();
        q.add(new Vertex(X, 0));
        min_distance[X] = 0;

        while (!q.isEmpty()) {
            Vertex pos = q.poll();

            int to = pos.to;

            if (visited[to])
                continue;
            visited[to] = true;

            for (Vertex next : list[to]) {
                if (min_distance[to] + next.cost < min_distance[next.to]) {
                    min_distance[next.to] = min_distance[to] + next.cost;
                    q.add(new Vertex(next.to, min_distance[next.to]));
                }
            }
        }

        return min_distance;
    }
}