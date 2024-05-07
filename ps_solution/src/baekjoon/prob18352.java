package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import baekjoon.prob1167.node;

public class prob18352 {
    static int N, M, K, X;
    static StringTokenizer st = null;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            graph[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
        }

        bfs();

        System.out.println(sb.toString());
    }

    private static void bfs() {
        visited = new boolean[N + 1];
        Queue<Integer> q = new ArrayDeque<>();

        visited[X] = true;
        q.add(X);

        int depth = 0;
        while (depth < K) {
            int size = q.size();
            while (size-- > 0) {
                int cur = q.poll();

                for (int next : graph[cur]) {
                    if (visited[next])
                        continue;

                    visited[next] = true;
                    q.add(next);
                }
            }
            depth++;
        }

        if (q.isEmpty()) {
            sb.append(-1);
        } else {
            List<Integer> nodeList = new ArrayList<>();
            while(!q.isEmpty()){
                nodeList.add(q.poll());
            }

            Collections.sort(nodeList);

            nodeList.forEach(o -> sb.append(o).append("\n"));
        }
    }
}
