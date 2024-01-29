package baekjoon;

import java.util.*;
import java.io.*;

public class prob1406_3 {
    static String s;
    static int cursur;
    static int M;
    static Stack<Character> L_stack;
    static Stack<Character> R_stack;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        L_stack = new Stack<>();
        R_stack = new Stack<>();

        s = br.readLine();
        for(int i=0;i<s.length();i++){
            L_stack.add(s.charAt(i));
        }

        M = Integer.parseInt(br.readLine());

        while(M --> 0){
            StringTokenizer st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();

            if(cmd.equals("L")){
                cmd_L();
            }else if(cmd.equals("D")){
                cmd_D();
            }else if(cmd.equals("B")){
                cmd_B();
            }else if(cmd.equals("P")){
                String input = st.nextToken();
                cmd_P(input);
            }else{
                System.out.println("invalid cmd");
                return;
            }
        }

        while(!L_stack.isEmpty()){
            R_stack.add(L_stack.pop());
        }

        StringBuilder sb = new StringBuilder();
        
        while(!R_stack.isEmpty()){
            sb.append(R_stack.pop());
        }

        System.out.println(sb.toString());
    }    
    static void cmd_L(){
        if(L_stack.isEmpty()){
            return;
        }
        R_stack.add(L_stack.pop());
        return;
    }
    static void cmd_D(){
        if(R_stack.isEmpty()){
            return;
        }
        L_stack.add(R_stack.pop());
    }
    static void cmd_B(){
        if(L_stack.isEmpty()){
            return;
        }
        L_stack.pop();
        return;
    }
    static void cmd_P(String input){
        L_stack.add(input.charAt(0));
        return;
    }
}
