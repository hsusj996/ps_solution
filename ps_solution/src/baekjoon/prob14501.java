package baekjoon;

import java.util.*;

public class prob14501 {
    static int N;
    static int max = 0;
    static int[] need_day;
    static int[] pay;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        need_day = new int[N+1];
        need_day[0] = 1;
        pay = new int[N+1];

        for (int i = 1; i <= N; i++) {
            need_day[i] = sc.nextInt();
            pay[i] = sc.nextInt();
        }

        dfs(0, 0);

        System.out.println(max);
    }

    static void dfs(int day, int sum_of_pay) {
        if (day + need_day[day] - 1 > N) {
            if (max < sum_of_pay) {
                max = sum_of_pay;
            }
            return;
        }

        sum_of_pay += pay[day];

        if(day + need_day[day] - 1 == N){
            if(max < sum_of_pay){
                max = sum_of_pay;
            }
            return;
        }

        for (int i = day + need_day[day]; i <= N; i++) {
            dfs(i, sum_of_pay);
        }
    }
}