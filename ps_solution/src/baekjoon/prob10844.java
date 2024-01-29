package baekjoon;

import java.io.*;

public class prob10844 {
    static final int MOD = 1000000000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] prev_arr = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        int ans = 0;

        
        for(int i=2; i<=N;i++){
            int[] arr = new int[10];
            for(int j=0;j<=9;j++){
                if(j == 0){
                    arr[j] = prev_arr[1]; 
                } else if(j == 9){
                    arr[j] = prev_arr[8];
                } else{
                    arr[j] = prev_arr[j-1] + prev_arr[j + 1];
                }
                arr[j] %= MOD;
            }
            prev_arr = arr;
        }

        for(int i=0;i<=9;i++){
            ans += prev_arr[i];
            ans %= MOD;
        }

        System.out.println(ans);
    }
}
