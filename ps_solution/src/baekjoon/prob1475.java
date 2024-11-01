package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob1475 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        
        int[] arr = new int[10];
        for(int i=0;i<s.length();i++){
            int n = s.charAt(i) - '0';

            if(n==9){
                arr[6]++;
            } else{
                arr[n]++;
            }
        }

        double num6 = (double) arr[6];
        arr[6] = (int) Math.ceil(num6 / 2);

        int max = 0;
        for(int i=0;i<10;i++){
            max = Math.max(max, arr[i]);
        }

        System.out.println(max);
    }
}
