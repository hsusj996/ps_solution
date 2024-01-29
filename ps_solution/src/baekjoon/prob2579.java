package baekjoon;

import java.util.Scanner;

public class prob2579 {
    static int T;
    static int[] stairs;
    static int[] max_score;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();
        stairs = new int[T + 1];
        max_score = new int[T + 1];

        for (int i = 1; i <= T; i++) {
            stairs[i] = sc.nextInt();
        }

        max_score[0] = stairs[0]; // 초기화
        if (T >= 1) {   // T가 1보다 작을 경우 오류가 발생하지 않도록
            max_score[1] = stairs[0] + stairs[1];
        }
        if (T >= 2) {   // T가 2보다 작을 경우 오류가 발생하지 않도록
            max_score[2] = stairs[1] + stairs[2];
        }

        int ans = dp(T);
        System.out.println(ans);
        sc.close();
        return;
    }

    public static int dp(int N) {
        if (max_score[N] == 0 && N != 0) {    //초기화되지 않은 경우 갱신
            int caseA = dp(N - 2);  
            int caseB = dp(N - 3) + stairs[N - 1];

            max_score[N] = Math.max(caseA, caseB) + stairs[N];  //A와 B중 큰 값을 선택
        }
        return max_score[N];
    }
}
