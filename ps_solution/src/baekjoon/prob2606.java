package baekjoon;

import java.util.Scanner;

public class prob2606 {
    static int N;
    static int M;
    static int tree[][];
    static boolean visited[];
    static int ans = 0;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        tree = new int[N + 1][N + 1]; //트리 배열 초기화
        visited = new boolean[N + 1]; //방문 정보 배열 초기화

        M = sc.nextInt();

        while(M-->0){   //간선 정보 입력
            int com1 = sc.nextInt();
            int com2 = sc.nextInt();

            tree[com1][com2] = 1;
            tree[com2][com1] = 1;
        }

        DFS(1); //DFS

        System.out.println(--ans);  //최초 노드는 제외

        sc.close();
        return;
    }

    public static void DFS(int n){
        if(!visited[n]){    //방문하지 않은 경우
            visited[n] = true;  //방문 처리
            ans++;
            for(int i=1;i<=N;i++){  //깊이 우선 탐색
                if(tree[n][i] == 1){    //연결되어 있다면
                    DFS(i);
                }
            }
        }else{
            return;
        }
    }
}
