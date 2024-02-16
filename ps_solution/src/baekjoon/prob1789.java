package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob1789 {
    static long N;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());

        System.out.println(BinarySearch());
    }
    private static long BinarySearch() {
        long start = 0;
        long end = N;
        long mid = 0;
        long max = 0;

        while(start <= end){
            mid = (start + end) / 2;

            long sum = (1 + mid) * mid / 2;
            if(sum == N){
                return mid;
            } else if(sum < N){
                max = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return max;
    }
}
