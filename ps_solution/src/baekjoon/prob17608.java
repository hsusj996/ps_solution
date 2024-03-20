package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class prob17608 {
    static Stack<Integer> stk;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        stk = new Stack<>();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());

            while (!stk.isEmpty()) {
                if (stk.peek() <= n) {
                    stk.pop();
                    continue;
                } else {
                    break;
                }
            }
            stk.push(n);
        }
        System.out.println(stk.size());
    }
}
