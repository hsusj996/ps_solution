package baekjoon;

import java.util.*;
import java.io.*;

public class prob1967 {
    static class vertex {
        int num;
        int distance;

        public vertex(int num, int distance) {
            this.num = num;
            this.distance = distance;
        }
    }

    static int N;
    static ArrayList<vertex>[] v_list;
    static boolean[] visited;
    static int max = 0;
    static int max_idx = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        v_list = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            v_list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            v_list[parent].add(new vertex(child, distance));
            v_list[child].add(new vertex(parent, distance));
        }
        visited = new boolean[N + 1];
        visited[1] = true;
        dfs(1, 0);

        visited = new boolean[N + 1];
        visited[max_idx] = true;
        dfs(max_idx, 0);
        System.out.println(max);
    }

    static void dfs(int idx, int sum) {
        if (max < sum) {
            max = sum;
            max_idx = idx;
        }

        for (vertex v : v_list[idx]) {
            if (!visited[v.num]) {
                visited[v.num] = true;
                dfs(v.num, sum + v.distance);
            }
        }
    }
}
