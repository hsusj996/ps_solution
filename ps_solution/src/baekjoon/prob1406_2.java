package baekjoon;

import java.util.*;
import java.io.*;

public class prob1406_2 {
    static String s;
    static int cursur;
    static int M;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        cursur = s.length();

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

        System.out.println(s);
    }    
    static void cmd_L(){
        if(cursur == 0){
            return;
        }
        cursur--;
        return;
    }
    static void cmd_D(){
        if(cursur == s.length()){
            return;
        }
        cursur++;
        return;
    }
    static void cmd_B(){
        if(cursur == 0){
            return;
        }
        s = s.substring(0, cursur - 1) + s.substring(cursur, s.length());
        cursur--;
        return;
    }
    static void cmd_P(String input){
        s = s.substring(0, cursur) + input + s.substring(cursur, s.length());
        cursur++;
        return;
    }
}
