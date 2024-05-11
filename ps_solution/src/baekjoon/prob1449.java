package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class prob1449 {
    static int N, L;
    static boolean[] hole = new boolean[1001];
    static int cnt = 0;
    static StringTokenizer st = null;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            hole[Integer.parseInt(st.nextToken())] = true;
        }

        for(int i=0;i<=1000;i++){
            if(hole[i]){
                cnt++;
                i += (L - 1);
            }
        }

        System.out.println(cnt);
    }
}
