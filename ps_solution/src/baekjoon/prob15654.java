package baekjoon;

import java.util.*;

public class prob15654 {
    static int N;
    static int M;
    static boolean[] visited;
    static int[] arr;
    static int[] selected_arr;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        visited = new boolean[N];
        arr = new int[N];
        selected_arr = new int[M];

        for(int i=0;i<N;i++){
            arr[i] = sc.nextInt();    
        }
        Arrays.sort(arr);

        dfs(0);
    }
    static void dfs(int depth){
        if(depth == M){
            for(int i=0;i<M;i++){
                System.out.print(selected_arr[i] + " ");
            }
            System.out.println();

            return;
        }

        for(int i=0;i<N;i++){
            if(visited[i]){
                continue;
            }

            visited[i] = true;
            selected_arr[depth] = arr[i];
            dfs(depth + 1);
            visited[i] = false;  
        }
    }

    static void selection_sort(int[] arr){
        for(int i=0;i<arr.length;i++){
            int min = i;

            for(int j=i+1;j<arr.length;j++){
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }

    static void swap(int[] arr, int n, int m){
        int tmp = arr[n];
        arr[n] = arr[m];
        arr[m] = tmp;
    }
}
