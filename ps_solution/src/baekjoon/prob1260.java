package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class prob1260 {
    static int graph[][];
    static boolean visit[];
    static int N;
    static int M;
    static int V;
    static Queue<Integer> bfsQue;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        V = sc.nextInt();

        bfsQue = new LinkedList<>();

        bfsQue.add(V);

        visit = new boolean[N + 1];

        graph = new int[N + 1][N + 1];  //그래프 생성
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = 0;
            }
        }
        for (int i = 0; i < M; i++) {   //그래프 입력
            int a = sc.nextInt();
            int b = sc.nextInt();

            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        for (int i = 0; i <= N; i++) {  //방문 정보 초기화
            visit[i] = false;
        }
        DFS(V);
        System.out.println();

        for (int i = 0; i <= N; i++) {  //방문 정보 초기화
            visit[i] = false;
        }
        BFS();
        sc.close();
        return;
    }

    public static void DFS(int start) {
        if(!visit[start]){  //방문하지 않았다면 방문
            System.out.print(start + " ");
            visit[start] = true;
            for(int i=1;i<=N;i++){
                if(graph[start][i] == 1){
                    DFS(i); //노드의 하위노드를 탐색
                }
            }
        }else{
            return;
        }
    }

    public static void BFS() {
        int start = bfsQue.poll();
        visit[start] = true;
        System.out.print(start + " ");

        for(int i=1;i<=N;i++){
            if(graph[start][i] == 1 && !visit[i]){
                bfsQue.add(i);
                visit[i] = true;
            }
        }

        if(!bfsQue.isEmpty()){
            BFS();
        }
    }
}