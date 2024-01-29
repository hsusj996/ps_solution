package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class prob11403 {
    static Queue<Integer> que = new LinkedList<>();
    static int N;
    static int[][] graph;
    static int[][] ans;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        graph = new int[N][N];
        ans = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < N; i++) {   //경로의 유무를 판단
            for (int j = 0; j < N; j++) {
                if (DFS(i, j)) {
                    ans[i][j] = 1;
                }else{
                    ans[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
        sc.close();
        return;
    }

    public static boolean DFS(int start, int end) { //DFS
        for (int i = 0; i < N; i++) {
            if (graph[start][i] == 1) {
                if (i == end) {
                    return true;
                } else {
                    return DFS(i, end);
                }
            }
        }
        return false;
    }
}
