package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob1309 {
    static int N;
    static final int MOD = 9901;
    static int[][] dp;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N][3];
        dp[0][0] = 1;   //0줄에 하나도 x
        dp[0][1] = 1;   //0줄에 왼쪽
        dp[0][2] = 1;   //0줄에 오른쪽
        
        for(int i=1;i<N;i++){
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % MOD;
            dp[i][1] = (dp[i-1][2] + dp[i-1][0]) % MOD;
            dp[i][2] = (dp[i-1][1] + dp[i-1][0]) % MOD;
        }

        System.out.println((dp[N-1][0] + dp[N-1][1] + dp[N-1][2]) % MOD);
    }
}
