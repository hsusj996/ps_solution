package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;

public class prob18111 {
    static int map[][];
    static int max = 0;
    static int min = 256;
    static int N, M ,B;
    static int min_time = 99999999;
    static int ans_height = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s1[] = br.readLine().split(" ");

        N = Integer.parseInt(s1[0]);
        M = Integer.parseInt(s1[1]);
        B = Integer.parseInt(s1[2]);

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s2[] = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(s2[j]);
                if(min > map[i][j]) min = map[i][j];
                if(max < map[i][j]) max = map[i][j];
            }
        }

        for (int i = 0; i <= max; i++) {
            int tmp = getTime(i);
            if(tmp == -1){
                break;
            }

            if (tmp <= min_time) {
                min_time = tmp;
                ans_height = i;
            }
        }
        System.out.println(min_time + " " + ans_height);
    }
    public static int getTime(int height) {                 //해당 높이의 시간값 구하기
        int time = 0;
        int block = B;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int tmp = Math.abs(height - map[i][j]);

                if (height >= map[i][j]) {                  //높이를 올려야 하는 경우
                    block -= tmp;
                    time += tmp;
                } else {                                    //줄여야 하는 경우
                    block += tmp;
                    time += (2 * tmp);
                }
            }
        }
        if(block < 0){
            return -1;
        }
        return time;
    }
}
