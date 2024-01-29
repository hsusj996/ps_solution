package baekjoon;

import java.util.*;
import java.io.*;

public class prob9465 {
    static int N;
    static int[][] sticker;
    static int[] max_select0;
    static int[] max_select1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            max_select0 = new int[N + 1];
            max_select1 = new int[N + 1];

            sticker = new int[2][N + 1];

            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int j = 1; j <= N; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            max_select0[0] = 0;
            max_select1[0] = 0;
            max_select0[1] = sticker[0][1];
            max_select1[1] = sticker[1][1];

            for(int i=2;i<=N;i++){
                max_select0[i] = Math.max(max_select1[i-1], max_select1[i-2]) + sticker[0][i];
                max_select1[i] = Math.max(max_select0[i-1], max_select0[i-2]) + sticker[1][i];  
            }

            System.out.println(Math.max(max_select0[N], max_select1[N]));
        }
    }

}
