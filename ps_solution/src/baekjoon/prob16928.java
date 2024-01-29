package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class prob16928 {
    static class node {
        int num;
        int depth;

        public node(int num, int depth) {
            this.num = num;
            this.depth = depth;
        }
    }

    static boolean visited[] = new boolean[101];
    static int ans = 0;
    static int[] snake_ladder_before;
    static int[] snake_ladder_after;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        snake_ladder_before = new int[N + M];
        snake_ladder_after = new int[N + M];
        int cnt = 0;

        while (N-- > 0) {
            snake_ladder_before[cnt] = sc.nextInt();
            snake_ladder_after[cnt] = sc.nextInt();
            cnt++;
        }
        while (M-- > 0) {
            snake_ladder_before[cnt] = sc.nextInt();
            snake_ladder_after[cnt] = sc.nextInt();
            cnt++;
        }

        // BFS
        Queue<node> que = new LinkedList<node>();
        que.add(new node(1, 0));
        visited[1] = true;

        while (!que.isEmpty()) {
            node cur_node = que.poll();

            if (cur_node.num == 100) {
                ans = cur_node.depth;
                break;
            }

            // 주사위 탐색, 뱀 사다리 탐색
            for (int i = cur_node.num + 1; i <= cur_node.num + 6 && i <= 100; i++) {
                int j = 0;
                for (; j < snake_ladder_before.length; j++) {
                    if (snake_ladder_before[j] == i && !visited[i]) {
                        que.add(new node(snake_ladder_after[j], cur_node.depth + 1));
                        visited[i] = true;
                        break;
                    }
                }
                if (j == snake_ladder_before.length && !visited[i]) {
                    que.add(new node(i, cur_node.depth + 1));
                    visited[i] = true;
                }
            }
        }

        System.out.println(ans);

        sc.close();
        return;
    }
}