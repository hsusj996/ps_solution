package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class prob11724{
    static int N;
    static int M;
    static int[][] tree;
    static boolean[] visited;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int ans = 0;
        N = sc.nextInt();
        M = sc.nextInt();

        tree = new int[N+1][N+1];

        for(int[] i:tree){
            Arrays.fill(i, 0);
        }
        
        visited = new boolean[N+1];

        for(int i=0;i<M;i++){
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();

            tree[node1][node2] = 1;
            tree[node2][node1] = 1;
        }

        for(int i=1;i<=N;i++){
            if(!visited[i]){
                DFS(i);
                ans++;
            }
        }

        System.out.println(ans);

        sc.close();
        return;
    }

    public static void DFS(int node){
        visited[node] = true;

        for(int i=1;i<=N;i++){
            if(!visited[i] && tree[node][i] == 1){
                DFS(i);
            }
        }
        return;
    }
}