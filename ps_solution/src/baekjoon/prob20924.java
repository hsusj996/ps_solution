package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import baekjoon.prob11779.edge;

public class prob20924 {
    static class Edge {
        int next;
        int d;

        public Edge(int next, int d) {
            this.next = next;
            this.d = d;
        }
    }

    static List<Edge>[] edgeList;
    static int N;
    static int R;
    static StringTokenizer st = null;
    static int gigaRoot;
    static int lengthCol;
    static int lengthBranch;
    static boolean[] visited;
    static int[] edgeCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        edgeCnt = new int[N + 1];
        edgeList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edgeList[i] = new ArrayList<>();
        }

        lengthBranch = 0;
        lengthCol = 0;

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            edgeList[v1].add(new Edge(v2, d));
            edgeList[v2].add(new Edge(v1, d));
            edgeCnt[v1]++;
            edgeCnt[v2]++;
        }

        visited[R] = true;
        if(edgeCnt[R] != 1){
            gigaRoot = R;
        }
        else{
            gigaRoot = edgeList[R].get(0).next;
            visited[gigaRoot] = true;
            lengthCol += edgeList[R].get(0).d;
            while(true){
                if(edgeCnt[gigaRoot] != 2){
                    break;
                }

                for(Edge e : edgeList[gigaRoot]){
                    if(!visited[e.next]){
                        visited[e.next] = true;
                        gigaRoot = e.next;
                        lengthCol += e.d;
                    }
                }
            }
        }

        dfs(gigaRoot, 0);

        System.out.println(lengthCol + " " + lengthBranch);
    }

    private static void dfs(int n, int length) {
        if (edgeCnt[n] == 1) {
            lengthBranch = Math.max(lengthBranch, length);
            return;
        }

        for (Edge e : edgeList[n]) {
            if (!visited[e.next]) {
                visited[e.next] = true;
                dfs(e.next, length + e.d);
            }
        }
    }
}
