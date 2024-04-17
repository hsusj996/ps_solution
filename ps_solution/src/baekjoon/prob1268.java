package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob1268 {
    static int N;
    static int[][] info;
    static int[][] classinfo;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        info = new int[N + 1][5];
        classinfo = new int[5][10];
        StringTokenizer st = null;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                info[i][j] = Integer.parseInt(st.nextToken());
                classinfo[j][info[i][j]]++;
            }
        }

        int min = 0;
        int hubo = 1;
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 0; j < 5; j++) {
                sum += (classinfo[j][info[i][j]] - 1);
            }

            if (min < sum) {
                min = sum;
                hubo = i;
            }
        }

        System.out.println(hubo);
    }
}
