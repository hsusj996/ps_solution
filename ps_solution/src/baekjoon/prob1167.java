package baekjoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class prob1167 {
    static int V;
    static ArrayList<node>[] list;
    static boolean[] visited;
    static int max;
    static int far_node;

    public static class node{
        int node;
        int distance;

        public node(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        V = sc.nextInt();

        list = new ArrayList[V+1];  //제네릭 type safety 문제 발생
        visited = new boolean[V+1];

        for(int i=1;i<V+1;i++){
            list[i] = new ArrayList<node>();
        }

        for(int i=0;i<V;i++){
            int vertex = sc.nextInt();

            while(true){
                int next = sc.nextInt();
                if(next == -1){
                    break;
                }
                int distance = sc.nextInt();

                list[vertex].add(new node(next, distance));
            }
        }

        visited[1] = true;
        DFS(1, 0);

        Arrays.fill(visited, false);

        visited[far_node] = true;
        DFS(far_node, 0);

        System.out.println(max);
        

        sc.close();
        return;
    }    
    static public void DFS(int node, int distance){
        if(distance > max){
            max = distance;
            far_node = node;
        }

        for(int i=0;i<list[node].size();i++){
            node n = list[node].get(i);

            if(!visited[n.node]){
                visited[n.node] = true;
                DFS(n.node, distance + n.distance);
            }
        }
    }
}
