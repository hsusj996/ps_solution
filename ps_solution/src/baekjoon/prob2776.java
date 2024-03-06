package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class prob2776 {
    static StringBuilder result = new StringBuilder();
    static StringTokenizer st = null;
    static int[] note1;
    static int T;
    static int N;
    static int M;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            note1 = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                note1[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(note1);

            M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                result.append(BinarySearch(Integer.parseInt(st.nextToken()))).append("\n");
            }
        }
        System.out.println(result);
    }

    private static int BinarySearch(int target) {
        int start = 0;
        int end = N - 1;
        int mid = 0;

        while(start <= end){
            mid = (start + end) / 2;

            if(note1[mid] == target){
                return 1;
            }

            if(note1[mid] > target){
                end = mid - 1;
            } else{
                start = mid + 1;
            }
        }

        return 0;
    }
}
