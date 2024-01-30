package baekjoon;

import java.io.*;

public class prob11057 {
    static int N;
    static long[][] ascending_cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        ascending_cnt = new long[1001][11];
        for(int i=0;i<10;i++){
            ascending_cnt[1][i] = 1;
        }
        ascending_cnt[1][10] = 10;

        for(int i=2;i<=N;i++){
            ascending_cnt[i][10] = ascending_cnt[i][0] = ascending_cnt[i-1][10];
            for(int j=1;j<10;j++){
                ascending_cnt[i][j] = ascending_cnt[i][j-1] - ascending_cnt[i-1][j-1] + 10007;
                ascending_cnt[i][j] %= 10007; 
                ascending_cnt[i][10] += ascending_cnt[i][j];
            }
            ascending_cnt[i][10] %= 10007;
        }

        System.out.println(ascending_cnt[N][10]);
    }
}
