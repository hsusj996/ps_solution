package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob1924 {
    static final int[] monthDay = {
        0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };
    static final String[] day = {
        "MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int dayDiff = 0;
        for(int i=1;i<x;i++){
            dayDiff += monthDay[i];
        }

        dayDiff += y;
        dayDiff--;

        System.out.println(day[dayDiff % 7]);
    }
}
