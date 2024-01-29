package baekjoon;

import java.io.*;
import java.util.*;

public class prob1916 {
    static class edge implements Comparable<edge> {
        int to;
        int cost;

        public edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(edge o) {
            // TODO Auto-generated method stub
            return min_cost[this.to] - min_cost[o.to];
        }
    }

    static int N;
    static int B;
    static ArrayList<edge>[] arr_edge;
    static int[] min_cost;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        B = Integer.parseInt(br.readLine());
        arr_edge = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            arr_edge[i] = new ArrayList<>();
        }
        min_cost = new int[N + 1];
        Arrays.fill(min_cost, Integer.MAX_VALUE);
        visited = new boolean[N + 1];

        for (int i = 0; i < B; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            arr_edge[v1].add(new edge(v2, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        dijkstra(start);

        System.out.println(min_cost[dest]);
    }

    static void dijkstra(int start) {
        PriorityQueue<edge> pq = new PriorityQueue<>();
        pq.add(new edge(start, 0));
        min_cost[start] = 0;

        while (!pq.isEmpty()) {
            edge now = pq.poll();

            if (visited[now.to]) {
                continue;
            }
            visited[now.to] = true;
            
            for (int i = 0; i < arr_edge[now.to].size(); i++) {
                edge next = arr_edge[now.to].get(i);

                int min = min_cost[now.to] + next.cost;
                min_cost[next.to] = Math.min(min, min_cost[next.to]);
                pq.add(next);
            }
        }
    }

}