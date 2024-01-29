package baekjoon;

import java.util.*;
import java.io.*;

public class prob2644 {
    static int N;
    static int A;
    static int B;
    static boolean[] visited;
    static node[] node_arr;

    static class node {
        int num;
        node parent;
        ArrayList<node> children;

        public node(int num) {
            this.num = num;
            this.children = new ArrayList<>();
        }

        public void set_parent(node parent) {
            this.parent = parent;
        }

        public void add_child(node child) {
            this.children.add(child);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        node_arr = new node[N + 1];
        for(int i=1;i<=N;i++){
            node_arr[i] = new node(i);
        }
        visited = new boolean[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        
        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            node_arr[nodeA].add_child(node_arr[nodeB]);
            node_arr[nodeB].set_parent(node_arr[nodeA]);
        }

        visited[A] = true;
        dfs(node_arr[A], 0);

        if(!visited[B]){
            System.out.println(-1);
        }
    }

    static void dfs(node now, int depth) {
        if (now.num == B) {
            System.out.println(depth);
            return;
        }

        if (!Objects.isNull(now.parent) && !visited[now.parent.num]) {
            visited[now.parent.num] = true;
            dfs(now.parent, depth + 1);
        }

        for (int i = 0; i < now.children.size(); i++) {
            if (!visited[now.children.get(i).num]) {
                visited[now.children.get(i).num] = true;
                dfs(now.children.get(i), depth + 1);
            }
        }
    }
}
