package baekjoon;

import java.io.*;
import java.util.*;

public class prob11725 {
    static int N;
    static ArrayList<Integer>[] node;
    static int[] parent;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        node = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            node[i] = new ArrayList<Integer>();
        }
        parent = new int[N + 1];

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            node[nodeA].add(nodeB);
            node[nodeB].add(nodeA);
        }

        q = new LinkedList<>();
        q.add(1);
        while (!q.isEmpty()) {
            int now = q.poll();

            for (int i = 0; i < node[now].size(); i++) {
                if (parent[node[now].get(i)] != 0) {
                    continue;
                }
                parent[node[now].get(i)] = now;
                q.add(node[now].get(i));
            }
        }

        for (int i = 2; i <= N; i++) {
            System.out.println(parent[i]);
        }
    }
}
