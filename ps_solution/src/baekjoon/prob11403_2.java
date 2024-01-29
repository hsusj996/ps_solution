package baekjoon;

import java.util.Scanner;

public class prob11403_2 {
    static int N;
    static int[][] graph;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        graph = new int[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                graph[i][j] = sc.nextInt();
            }
        }

        while(true){
            int cnt = 0;

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(graph[i][j] == 1){
                        for(int k=0;k<N;k++){
                            if(graph[j][k] == 1 && graph[i][k] == 0){
                                cnt++;
                                graph[i][k] = 1;
                            }
                        }
                    }
                }
            }

            if(cnt == 0){
                break;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }

        sc.close();
        return;
    }
}
