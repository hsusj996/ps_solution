package baekjoon;

import java.util.HashMap;
import java.util.Scanner;

public class prob2667 {
    static int N;
    static int[][] map;
    static int[] d_row = { -1, 0, 1, 0 };   //상하좌우 행
    static int[] d_col = { 0, -1, 0, 1 };   //상하좌우 열
    static HashMap<Integer, Integer> hash = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N][N];
        int key_num = 1;    //단지 번호
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            String str = sc.next();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {   //단지 발견
                    hash.put(key_num, 0);   //hashmap 삽입
                    DSF(i, j, key_num++);   //단지 dsf 탐색
                }
            }
        }

        int[] ans = new int[hash.size()];
        for (Integer value : hash.values()) {   //배열 전환
            ans[cnt++] = value;
        }

        for (int i = 0; i < ans.length; i++) {  //오름차순 정렬 (버블)
            for (int j = i + 1; j < ans.length; j++) {
                if (ans[i] > ans[j]) {
                    int tmp = ans[i];
                    ans[i] = ans[j];
                    ans[j] = tmp;
                }
            }
        }

        System.out.println(cnt);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
        sc.close();
        return;
    }

    public static void DSF(int row, int col, int num) {
        map[row][col] = 0;  //방문
        hash.put(num, hash.get(num) + 1);   //집 수 + 1

        for (int n = 0; n < 4; n++) {
            int next_row = row + d_row[n];
            int next_col = col + d_col[n];
            if (next_row >= 0 && next_row < N && next_col >= 0 && next_col < N) {   //갈 수 있는 곳일때
                if (map[next_row][next_col] == 1) { //집이 있으며 방문하지 않았을때
                    DSF(next_row, next_col, num);
                }
            } else {
                continue;
            }
        }
    }
}
