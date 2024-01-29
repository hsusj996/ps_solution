package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class prob7576 {
    static int N;
    static int M;
    static int[][] tomato;
    static int d_row[] = { -1, 0, 1, 0 };
    static int d_col[] = { 0, -1, 0, 1 };
    static Queue<int[]> que = new LinkedList<>();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tomato[i][j] = sc.nextInt();
            }
        }

        System.out.println(tomato_day());

        sc.close();
        return;
    }

    public static int tomato_day() {

        int ans = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tomato[i][j] == 1) {
                    que.add(new int[] { i, j, 0 });
                }
            }
        }

        ans = DFS();

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(tomato[i][j] == 0){
                    return -1;
                }
            }
        }

        return ans;
    }

    public static int DFS() {
        int result = 0;
        while (!que.isEmpty()) {
            int[] now_tomato = que.poll();

            for (int i = 0; i < 4; i++) {
                int row = now_tomato[0] + d_row[i];
                int col = now_tomato[1] + d_col[i];

                if (row >= 0 && row < N && col >= 0 && col < M) {
                    if (tomato[row][col] == 0) {
                        tomato[row][col] = 1;
                        que.add(new int[] { row, col, now_tomato[2] + 1 });
                    }
                }
            }
            result = now_tomato[2];
        }
        return result;
    }
}
