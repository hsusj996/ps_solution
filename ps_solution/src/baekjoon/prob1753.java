package baekjoon;

import java.util.*;
import java.io.*;

public class prob1753 {
    // vertex 클래스
    static class vertex implements Comparable<vertex> {
        int num;
        int d_from_start;
        Queue<edge> next_v;

        public vertex(int num) {
            this.num = num;
            this.next_v = new LinkedList<>();
            this.d_from_start = Integer.MAX_VALUE;
        }

        public void add_next_vertex(edge next_v) {
            this.next_v.add(next_v);
        }

        @Override
        public int compareTo(vertex o) {
            return this.d_from_start - o.d_from_start;
        }
    }

    static class edge {
        int num;
        int distance;

        public edge(int num, int distance) {
            this.num = num;
            this.distance = distance;
        }
    }

    static vertex[] arr_vertex;
    // static boolean[] visited;
    static int V;
    static int E;
    static int start;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 노드 초기화
        V = Integer.parseInt(st.nextToken());
        arr_vertex = new vertex[V + 1];
        // visited = new boolean[V + 1];
        for (int i = 1; i <= V; i++) {
            arr_vertex[i] = new vertex(i);
        }

        E = Integer.parseInt(st.nextToken());

        start = Integer.parseInt(br.readLine());
        arr_vertex[start].d_from_start = 0;

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int v_1 = Integer.parseInt(st.nextToken());
            int v_2 = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            arr_vertex[v_1].add_next_vertex(new edge(v_2, distance));
        }

        bfs();

        ResultPrint();
    }

    static void bfs() {
        PriorityQueue<vertex> q = new PriorityQueue<>();

        q.add(arr_vertex[start]);

        while (!q.isEmpty()) {
            vertex now = q.poll();

            while (!now.next_v.isEmpty()) {
                edge next = now.next_v.poll();

                int d_from_start = arr_vertex[now.num].d_from_start + next.distance;

                if (arr_vertex[next.num].d_from_start > d_from_start) {
                    arr_vertex[next.num].d_from_start = d_from_start;
                }
                q.add(arr_vertex[next.num]);
            }
        }
    }

    static void ResultPrint() {
        for (int i = 1; i <= V; i++) {
            if (arr_vertex[i].d_from_start == Integer.MAX_VALUE) {
                System.out.println("INF");
                continue;
            }

            System.out.println(arr_vertex[i].d_from_start);
        }
    }
}
