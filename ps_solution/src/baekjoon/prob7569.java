package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class prob7569 {
    static int N;
    static int M;
    static int H;
    static Queue<int[]> que;
    static int[][][] tomato;
    static int[] d_row = { -1, 0, 1, 0, 0, 0 }; //상 하 좌 우 위 아래
    static int[] d_col = { 0, -1, 0, 1, 0, 0 };
    static int[] d_hei = { 0, 0, 0, 0, 1, -1 };
    static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        que = new LinkedList<>();
        N = sc.nextInt();
        M = sc.nextInt();
        H = sc.nextInt();

        tomato = new int[H][M][N];

        for (int i = 0; i < H; i++) {   //입력
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    tomato[i][j][k] = sc.nextInt();
                    if (tomato[i][j][k] == 1) {
                        que.add(new int[] { i, j, k, 0 });  //최초에는 0일차 (depth = 0)
                    }
                }
            }
        }

        while (!que.isEmpty()) {
            int now_tomato[] = que.poll();

            for (int i = 0; i < 6; i++) {
                int height = now_tomato[0] + d_hei[i];
                int column = now_tomato[1] + d_col[i];
                int row = now_tomato[2] + d_row[i];

                if (height >= 0 && height < H && column >= 0 && column < M && row >= 0 && row < N) {    //범위에 벗어나지 않도록 해야함
                    if(tomato[height][column][row] == 0){
                        tomato[height][column][row] = 1;
                        que.add(new int[]{height, column, row, now_tomato[3] + 1}); //depth + 1
                    }
                }
            }
            ans = now_tomato[3];
        }

        for (int i = 0; i < H; i++) {   //안익은 토마토가 있는 경우 -1
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    if (tomato[i][j][k] == 0) {
                        System.out.println(-1);
                        sc.close();
                        return;
                    }
                }
            }
        }

        System.out.println(ans);
        sc.close();
        return;
    }
}
