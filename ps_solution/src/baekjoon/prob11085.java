package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class prob11085 {
    static class Edge implements Comparable<Edge>{
        int v1;
        int v2;
        int weight;
        public Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return o.weight - this.weight;
        }

        
    }
    static StringTokenizer st = null;
    static int p, w;
    static int start, end;
    static int[] parents = new int[1000];
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        for(int i=0;i<w;i++){
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            pq.add(new Edge(v1, v2, weight));
        }

        MakeSet();
        while(!pq.isEmpty()){
            Edge cur = pq.poll();

            if(!Union(cur.v1, cur.v2)){
                continue;
            }

            if(FindSet(start) == FindSet(end)){
                System.out.println(cur.weight);
                break;
            }
        }
    }

    private static void MakeSet(){
        for(int i=0;i<1000;i++){
            parents[i] = i;
        }
    }

    private static int FindSet(int a){
        if(parents[a] == a){
            return a;
        }

        return parents[a] = FindSet(parents[a]);
    }

    private static boolean Union(int a, int b){
        int aRoot = FindSet(a);
        int bRoot = FindSet(b);

        if(aRoot == bRoot){
            return false;
        }
        parents[bRoot] = aRoot;
        return true;
    }
    
}
