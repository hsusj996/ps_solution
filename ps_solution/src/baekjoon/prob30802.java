package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob30802 {
    static int N;
    static int[] arr = new int[6];
    static int T, P;
    static StringTokenizer st = null;
    static StringBuilder sb=  new StringBuilder();
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<6;i++){
            arr[i] = Integer.parseInt(st.nextToken());    
        }

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        int t = 0;
        for(int i=0;i<6;i++){
            t += arr[i] / T;
            t += arr[i] % T > 0 ? 1 : 0; 
        }
        sb.append(t).append("\n");

        sb.append(N / P).append(" ").append(N % P);

        System.out.println(sb.toString());
    }
}
