package baekjoon;

import java.util.*;
import java.io.*;

public class prob10819 {
    static int N;
    static int[] arr1;
    static int[] arr2;
    static boolean[] visited;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr1 = new int[N];
        arr2 = new int[N];
        visited = new boolean[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0);

        System.out.println(ans);
    }

    static void dfs(int depth) {
        if (depth == N) {
            int result = Cal(arr2);
            if (result > ans) {
                ans = result;
            }
            return;
        }

        for(int i=0;i<N;i++){
            if(visited[i]){
                continue;
            }

            visited[i] = true;
            arr2[depth] = arr1[i];
            dfs(depth + 1);
            visited[i] = false; 
        }
    }

    static int Cal(int[] arr) {
        int sum = 0;
        for (int i = 0; i < N - 1; i++) {
            sum += Math.abs(arr[i] - arr[i + 1]);
        }

        return sum;
    }
}
