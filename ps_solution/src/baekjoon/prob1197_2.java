package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class prob1197_2 {
    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }

    }

    static int V;
    static int E;
    static Edge[] edgeArr;
    static int[] parents;
    static int weightSum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parents = new int[V + 1];
        edgeArr = new Edge[E];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edgeArr[i] = new Edge(from, to, weight);
        }
        Arrays.sort(edgeArr);
        MakeSet();

        int cnt = 0;
        for (int i = 0; i < E; i++) {
            if (Union(edgeArr[i].from, edgeArr[i].to)) {
                weightSum += edgeArr[i].weight;
                cnt++;
            }

            if (cnt == E - 1) {
                break;
            }
        }

        System.out.println(weightSum);
    }

    private static boolean Union(int a, int b) {
        int aRoot = FindSet(a);
        int bRoot = FindSet(b);

        if (aRoot == bRoot) {
            return false;
        }

        parents[bRoot] = aRoot;
        return true;
    }

    private static int FindSet(int a) {
        if (parents[a] == a) {
            return a;
        }

        return parents[a] = FindSet(parents[a]);
    }

    private static void MakeSet() {
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }
    }

}
