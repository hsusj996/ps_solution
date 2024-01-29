package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class prob2178 {
    static int N;
    static int M;
    static int miro[][];
    static boolean visit[];
    static int tree[][];
    static Queue<int[]> que;

    public static void main(String[] args) {
        int row_val[] = { -1, 0, 1, 0 };
        int col_val[] = { 0, -1, 0, 1 };

        Scanner sc = new Scanner(System.in);

        que = new LinkedList<>();

        N = sc.nextInt();
        M = sc.nextInt();

        miro = new int[N][M];
        tree = new int[N * M + 1][N * M + 1];
        visit = new boolean[N * M + 1];

        for (int i = 0; i < N; i++) {
            String str = sc.next();
            for (int j = 0; j < M; j++) {
                miro[i][j] = str.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (miro[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        if (i + row_val[k] >= 0 && i + row_val[k] < N
                                && j + col_val[k] >= 0 && j + col_val[k] < M) {
                            if (miro[i + row_val[k]][j + col_val[k]] == 1) {
                                int tmp1 = i * M + j + 1;
                                int tmp2 = (i + row_val[k]) * M + j + col_val[k] + 1;
                                tree[tmp1][tmp2] = 1;
                                tree[tmp2][tmp1] = 1;
                            }
                        }
                    }
                }
            }
        }
        
        que.add(new int[] { 1, 1 });
        System.out.println(BFS());

        sc.close();
        return;
    }

    public static int BFS() {
        int tmp[] = que.poll();
        visit[tmp[0]] = true;
        if (tmp[0] == N * M) {
            return tmp[1];
        }

        for (int i = 0; i <= N * M; i++) {
            if (tree[tmp[0]][i] == 1 && !visit[i]) {
                que.add(new int[] { i, tmp[1] + 1 });
            }
        }
        return BFS();
    }
}
