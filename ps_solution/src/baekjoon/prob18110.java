package baekjoon;

import java.util.Comparator;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class prob18110 {
    public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

        int[] rate_arr = new int[n];

        for (int i = 0; i < n; i++) {
            rate_arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(rate_arr);

        int cutline = (int) Math.round((double) n * 0.15);

        int hap = 0;
        for (int i = cutline; i < n - cutline; i++) {
            hap += rate_arr[i];
        }

        int result = (int) Math.round((double) hap / (n - cutline * 2));

        System.out.println(result);
    }
}
