package baekjoon;

import java.io.*;
import java.util.*;

public class prob1918 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        System.out.println(PostFix(s));
    }

    static String PostFix(String s) {
        int length = s.length();
        Stack<Character> stck = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);

            switch (c) {
                case '+':
                case '-':
                case '*':
                case '/':
                    while (!stck.isEmpty() && priority(stck.peek()) >= priority(c)) {
                        sb.append(stck.pop());
                    }
                    stck.add(c);
                    break;
                case '(':
                    stck.add(c);
                    break;
                case ')':
                    while (!stck.isEmpty() && stck.peek() != '(') {
                        sb.append(stck.pop());
                    }
                    stck.pop();
                    break;
                default:
                    sb.append(c);
            }
        }
        while(!stck.isEmpty()){
            sb.append(stck.pop());
        }

        return sb.toString();
    }

    static int priority(char operator) {
        if (operator == '(' || operator == ')') {
            return 0;
        } else if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        }
        return -1;
    }
}
