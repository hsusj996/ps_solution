package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class prob1697 {
    static int N;   //수빈이 위치
    static int K;   //동생 위치
    static int ans;
    static Queue<int[]> que;
    static boolean visit[]; //방문 여부

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        que = new LinkedList<>();
        N = sc.nextInt();
        K = sc.nextInt();

        visit = new boolean[1000001];

        que.add(new int[] { N, 0 });    //최상위 노드 삽입

        BFS();

        System.out.println(ans);

        sc.close();
    }

    public static void BFS() {
        int tmp[] = que.poll();
        visit[tmp[0]] = true;
        if (tmp[0] == K) {  //동생의 위치라면 탐색종료
            ans = tmp[1];
            return;
        }

        if (tmp[0] + 1 <= 100000) { //+1 경우
            if (!visit[tmp[0] + 1]) {
                que.add(new int[] { tmp[0] + 1, tmp[1] + 1 });
            }
        }
        if (tmp[0] - 1 >= 0) {  //-1 경우
            if (!visit[tmp[0] - 1]) {
                que.add(new int[] { tmp[0] - 1, tmp[1] + 1 });
            }
        }
        if (tmp[0] * 2 <= 100000) { //*2 경우
            if (!visit[tmp[0] * 2]) {
                que.add(new int[] { tmp[0] * 2, tmp[1] + 1 });
            }
        }

        BFS();  //재귀
    }
}
