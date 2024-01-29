package baekjoon;

import java.util.Scanner;

public class prob10811 {
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] bucket = new int[N];
        for(int i=0;i<bucket.length;i++){
            bucket[i] = i + 1;
        }

        while(M --> 0){
            int i = sc.nextInt() - 1;
            int j = sc.nextInt() - 1;

            while(i<j){
                int tmp = bucket[i];
                bucket[i] = bucket[j];
                bucket[j] = tmp;

                i++;
                j--;
            }
        }

        for(int i=0;i<bucket.length;i++){
            sb.append(bucket[i] + " ");
        }

        System.out.println(sb.toString());

        sc.close();
        return;
    }
}
