package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob1003 {
    static int ans[][] = new int[41][2];

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            ans[0][0] = 1;
            ans[0][1] = 0;
            ans[1][0] = 0;
            ans[1][1] = 1;

            for(int i=2;i<41;i++){
                ans[i][0] = ans[i-1][0] + ans[i-2][0];
                ans[i][1] = ans[i-1][1] + ans[i-2][1];
            }

            System.out.println(ans[N][0] + " " + ans[N][1]);
        }
    }
}
