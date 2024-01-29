package baekjoon;

import java.util.*;

public class prob2193_2 {
    static int N;
    static long[] end_0;
    static long[] end_1;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        end_0 = new long[N];
        end_1 = new long[N];

        end_0[0] = 0;
        end_1[0] = 1;

        for(int i=1;i<N;i++){
            end_0[i] = end_0[i-1] + end_1[i-1];
            end_1[i] = end_0[i-1];
        }

        long ans = end_0[N-1] + end_1[N-1];
        
        System.out.println(ans);
    }
}
