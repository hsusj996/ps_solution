package baekjoon;

import java.util.*;
import java.io.*;

public class prob1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int cnt = N;
            int[] map = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());

                int rank1 = Integer.parseInt(st.nextToken());
                int rank2 = Integer.parseInt(st.nextToken());

                map[rank1] = rank2;
            }

            int high_rank = map[1];
            for (int i = 2; i <= N; i++) {
                if(map[i] > high_rank){
                    cnt--;
                    continue;
                }
                high_rank = map[i];
            }
            System.out.println(cnt);
        }
    }
}
