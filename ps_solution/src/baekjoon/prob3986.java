package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class prob3986 {
    static int N;
    static int ans = 0;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            Stack<Character> stk = new Stack<>();

            for (int j = 0; j < s.length(); j++) {
                if (stk.isEmpty()) {
                    stk.push(s.charAt(j));
                    continue;
                }

                if (stk.peek() == s.charAt(j)) {
                    stk.pop();
                } else{
                    stk.push(s.charAt(j));
                }
            }

            if(stk.isEmpty()){
                ans++;
            }
        }

        System.out.println(ans);
    }
}
