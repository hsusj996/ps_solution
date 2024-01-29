package baekjoon;

import java.util.*;

public class prob14501_2 {
    static int N;
    static int[] need_day;
    static int[] pay;
    static int[] max;
    static int current = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        need_day = new int[N + 1];
        need_day[0] = 1;
        pay = new int[N + 1];
        max = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            need_day[i] = sc.nextInt();
            pay[i] = sc.nextInt();
        }

        if (need_day[N] == 1) {
            max[N] = pay[N];
        } else {
            max[N] = 0;
        }
        current = max[N];

        for (int i = N - 1; i >= 0; i--) {
            if (i + need_day[i] - 1 < N) {
                max[i] = pay[i] + max[i + need_day[i]];
            } else if (i + need_day[i] - 1 == N) {
                max[i] = pay[i];
            } else {
                max[i] = 0;
            }
            
            if(max[i] < current){
                max[i] = current;
            }else{
                current = max[i];
            }
        }

        System.out.println(current);
    }
}