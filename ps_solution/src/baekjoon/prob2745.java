package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob2745 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        String s = st.nextToken();
        int B = Integer.parseInt(st.nextToken());
        int ans = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            int v = 0;
            if(c >= 'A'){
                v = c - 'A' + 10;
            } else{
                v = c - '0';
            }

            ans += (v * Math.pow(B, s.length() - 1 - i));
        }

        System.out.println(ans);
    }
}
