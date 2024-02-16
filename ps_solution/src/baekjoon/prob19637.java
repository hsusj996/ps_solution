package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob19637 {
    static int[] arr;
    static String[] strArr;
    static int N, M;
    static StringBuilder result = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        strArr = new String[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            strArr[i] = st.nextToken();
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(br.readLine());
            result.append(BinarySearch(target)).append("\n");
        }

        System.out.println(result);
    }
    private static String BinarySearch(int target) {
        int start = 0;
        int end = N;
        int min = 0;

        while(start <= end){
            int mid = (start + end) / 2;

            if(arr[mid] < target){
                min = mid + 1;
                start = mid + 1;
            } else{
                end = mid - 1;
            }
        }

        return strArr[min];
    }
}
