package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob11723_3 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int bit_set = 0;

        int M = Integer.parseInt(br.readLine());

        while(M --> 0){
            StringTokenizer st =new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int num;
            switch(cmd){
                case "add":
                    num = Integer.parseInt(st.nextToken());
                    bit_set |= (1 << (num -1));
                    break;
                case "remove":
                    num = Integer.parseInt(st.nextToken());
                    bit_set = bit_set & ~(1<<(num-1));
                    break;
                case "check":
                    num = Integer.parseInt(st.nextToken());
                    sb.append((bit_set & (1 << (num - 1))) != 0 ? "1\n" : "0\n");
                    break;
                case "toggle":
                    num = Integer.parseInt(st.nextToken());
                    bit_set ^= (1 << (num-1));
                    break;
                case "all":
                    bit_set |= (~0);
                    break;
                case "empty":
                    bit_set &= 0;
                    break;
            }
        }
        System.out.println(sb.toString());
        return;
    }
}
