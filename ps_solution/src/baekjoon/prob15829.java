package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class prob15829 {
    public static void main(String []args) throws NumberFormatException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(br.readLine());
        char ch = 'a';
        long ans = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        for(int i=1;i<=26;i++){
            map.put(ch++, i);
        }

        long pow = 1;
        String str = br.readLine();

        for(int i=0;i<L;i++){
            int buf = map.get(str.charAt(i));

            long tmp = (long) (buf * pow);

            pow = (pow*31)%1234567891;

            ans += tmp;
        }
        ans = ans % 1234567891;

        System.out.println(ans);
    }
}
