package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class prob1389 {
    static class node {
        public int num;
        public int depth;

        public node(int num, int depth) {
            this.num = num;
            this.depth = depth;
        }
    }

    static int N;
    static int M;
    static int graph[][];
    static boolean visit[];
    static Queue<node> que;
    static int sum;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        que = new LinkedList<>();

        int ans = Integer.MAX_VALUE;
        int index = 0;

        N = sc.nextInt();
        M = sc.nextInt();

        visit = new boolean[N + 1];
        graph = new int[N + 1][N + 1]; // 그래프 초기화
        for (int i = 0; i < N; i++) {
            graph[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        for (int i = 1; i <= N; i++) {
            node tmp = new node(i, 0);
            sum = 0;

            for (int j = 0; j <= N; j++) { // 방문 정보 초기화
                visit[j] = false;
            }

            que.add(tmp);
            BFS();
            que.clear();

            if (ans > sum) {
                ans = sum;
                index = i;
            }
        }
        System.out.println(index);
        sc.close();
    }

    public static void BFS() {  //BFS
        node start = que.poll();
        sum += start.depth;
        visit[start.num] = true;

        for (int i = 1; i <= N; i++) {
            if (graph[start.num][i] == 1 && !visit[i]) {    //방문하지 않은 하위노드일 경우 큐에 삽입
                que.add(new node(i, start.depth + 1));  //하위노드이기 때문에 depth + 1
                visit[i] = true;
            }
        }

        if (!que.isEmpty()) {
            BFS();
        }
    }
}