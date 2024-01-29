package baekjoon;

import java.util.*;
import java.io.*;

public class prob1504 {
    static class LinkedVertex implements Comparable<LinkedVertex> {
        int vertex;
        int distance;

        public LinkedVertex(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(LinkedVertex o) {
            return this.distance - o.distance;
        }
    }

    static ArrayList<LinkedVertex>[] vertex_info;
    static int num_N;
    static int num_V;
    static int stop_overA;
    static int stop_overB;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        num_N = Integer.parseInt(st.nextToken());
        num_V = Integer.parseInt(st.nextToken());

        vertex_info = new ArrayList[num_N + 1];

        for (int i = 0; i < num_N + 1; i++) {
            vertex_info[i] = new ArrayList<>();
        }

        for (int i = 0; i < num_V; i++) {
            st = new StringTokenizer(br.readLine());

            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            vertex_info[nodeA].add(new LinkedVertex(nodeB, distance));
            vertex_info[nodeB].add(new LinkedVertex(nodeA, distance));
        }

        st = new StringTokenizer(br.readLine());
        stop_overA = Integer.parseInt(st.nextToken());
        stop_overB = Integer.parseInt(st.nextToken());

        int dijkstra_from_1[] = dijkstra(1);
        int dijkstra_from_A[] = dijkstra(stop_overA);
        int dijkstra_from_B[] = dijkstra(stop_overB);

        int sum_of_caseA = dijkstra_from_1[stop_overA] + dijkstra_from_A[stop_overB]
        + dijkstra_from_B[num_N];
        int sum_of_caseB = dijkstra_from_1[stop_overB] + dijkstra_from_B[stop_overA]
        + dijkstra_from_A[num_N];

        if(sum_of_caseA < 0 && sum_of_caseB < 0){
            System.out.println(-1);
        }else{
            System.out.println(Math.min(sum_of_caseA, sum_of_caseB));
        }
    }

    static int[] dijkstra(int start_node) {
        int min_distance[] = new int[num_N + 1];
        Arrays.fill(min_distance, Integer.MAX_VALUE);
        boolean visited[] = new boolean[num_N + 1];

        Queue<LinkedVertex> q = new PriorityQueue<>();

        q.add(new LinkedVertex(start_node, 0));
        min_distance[start_node] = 0;

        while (!q.isEmpty()) {
            LinkedVertex v = q.poll();

            int to = v.vertex;

            if (visited[to]) {
                continue;
            }
            visited[to] = true;

            for (LinkedVertex next : vertex_info[to]) {
                int distance_to_next = min_distance[to] + next.distance;

                if (distance_to_next <= min_distance[next.vertex]) {
                    min_distance[next.vertex] = distance_to_next;
                    q.add(new LinkedVertex(next.vertex, min_distance[next.vertex]));
                }
            }
        }

        return min_distance;
    }
}
