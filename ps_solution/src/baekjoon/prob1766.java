package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class prob1766 {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st = null;
    static int N, M;
    static List<Integer>[] graph;
    static int[] inDegree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inDegree = new int[N + 1];
        graph = new ArrayList[N + 1];
        for(int i=1;i<=N;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());

            graph[p1].add(p2);
            inDegree[p2]++;
        }

        topologySort();

        System.out.println(sb.toString());
    }

    private static void topologySort() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=1;i<=N;i++){
            if(inDegree[i] == 0){
                pq.add(i);
            }
        }

        while(!pq.isEmpty()){
            int cur = pq.poll();

            sb.append(cur).append(" ");

            for(int next : graph[cur]){
                inDegree[next]--;
                if(inDegree[next] == 0){
                    pq.add(next);
                }
            }
        }
    }
}
