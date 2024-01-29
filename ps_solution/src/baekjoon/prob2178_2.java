package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class prob2178_2 {
    static int N;
    static int M;
    static int miro[][];
    static boolean visit[][];
    static int ans;
    static int[] row_val = { -1, 0, 1, 0 };
    static int[] col_val = { 0, -1, 0, 1 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        miro = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = sc.next();
            for (int j = 0; j < M; j++) {
                miro[i][j] = str.charAt(j) - '0';
            }
        }
        BFS(0, 0);
        System.out.println(miro[N-1][M-1]); //끝 노드의 값이 깊이값 (최소거리)

        sc.close();
        return;
    }

    public static void BFS(int row, int col) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] { row, col });

        while (!que.isEmpty()) {    //큐가 비어있을 때까지 반복
            int now[] = que.poll();

            int now_row = now[0];
            int now_col = now[1];

            visit[now_row][now_col] = true;

            for (int i = 0; i < 4; i++) {   //상하좌우 노드 체크
                int next_row = now_row + row_val[i];
                int next_col = now_col + col_val[i];

                if (next_row < 0 || next_col < 0 || next_row >= N || next_col >= M) {   //범위에 벗어나면 outOfBound 발생
                    continue;
                }

                if (!visit[next_row][next_col] && miro[next_row][next_col] == 1) {  //방문하지 않았고 인접노드라면 큐 삽입
                    que.add(new int[] { next_row, next_col});
                    miro[next_row][next_col] = miro[now_row][now_col] + 1;  //깊이는 이전 연결된 노드의 +1
                }
            }
        }
    }
}
