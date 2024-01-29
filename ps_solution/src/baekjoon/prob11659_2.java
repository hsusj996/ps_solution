package baekjoon;

import java.util.Scanner;

public class prob11659_2 {
    static int N;
    static int M;
    static int[] arr;
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N + 1];
        arr[0] = 0;
        for(int i=1;i<=N;i++){
            int input = sc.nextInt();

            arr[i] = arr[i-1] + input;
        }

        while(M -->0){
            int left = sc.nextInt();
            int right = sc.nextInt();

            System.out.println(arr[right] - arr[left - 1]);
        }
        sc.close();
        return;
    }
}
