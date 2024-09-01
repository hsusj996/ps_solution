package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob30804 {
    static int N;
    static int[] arr;
    static int[] cnt = new int[10];
    static int max = -1;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int p1 = 0;
        int p2 = 0;
        while (p2 < N) {
            cnt[arr[p2++]]++;

            while(10 - count(cnt) > 2){
                cnt[arr[p1++]]--;
            }

            max = Math.max(max, p2 - p1);
        }

        System.out.println(max);
    }
    public static int count(int[] arr){
        int cnt = 0;
        for(int i : arr){
            if(i == 0){
               cnt++;
            }
        }
        return cnt;
    }
}
