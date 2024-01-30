package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class prob14938 {
    static class edge {
        int v1;
        int v2;
        int cost;

        public edge(int v1, int v2, int cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }
    }

    static final int INF = 1_000_000_000;
    static edge[] edgeArr;
    static int[] items;
    static int[][] minDistance;
    static int N;
    static int M;
    static int R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        items = new int[N + 1];
        minDistance = new int[N + 1][N + 1];
        edgeArr = new edge[R];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edgeArr[i] = new edge(v1, v2, cost);
        }

        FloydInit();
        FloyedWarshall();

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                if (minDistance[i][j] <= M) {
                    sum += items[j];
                }
            }
            ans = Math.max(ans, sum);
        }
        System.out.println(ans);
    }

    private static void FloyedWarshall() {
        for (int round = 1; round <= N; round++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    minDistance[i][j] = Math.min(minDistance[i][j], minDistance[i][round] + minDistance[round][j]);
                }
            }
        }
    }

    static void FloydInit() {
        for (int i = 1; i <= N; i++) {
            Arrays.fill(minDistance[i], INF);
        }

        for (int i = 0; i < R; i++) {
            int v1 = edgeArr[i].v1;
            int v2 = edgeArr[i].v2;
            int cost = edgeArr[i].cost;

            minDistance[v1][v2] = Math.min(minDistance[v1][v2], cost);
            minDistance[v2][v1] = Math.min(minDistance[v2][v1], cost);
        }

        for (int i = 1; i <= N; i++) {
            minDistance[i][i] = 0;
        }
    }
}
