package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class prob9019 {
    static int T;
    public static void main(String[] args) throws NumberFormatException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Scanner sc= new Scanner(System.in);

        T = Integer.parseInt(br.readLine());

        while(T-->0){
            Queue<String[]> que = new LinkedList<>();
            String input[] = br.readLine().split(" ");
            String before_str = input[0];
            String after_str = input[1];

            que.add(new String[]{before_str, ""});

            while(!que.isEmpty()){
                StringBuilder sb = new StringBuilder();
                String now_str[] = que.poll();
                int tmp = 0;

                if(Integer.parseInt(now_str[0]) == Integer.parseInt(after_str)){
                    System.out.println(now_str[1]);
                    break;
                }
                tmp = Integer.parseInt(now_str[0]);
                int tmp_D = tmp * 2;
                if(tmp_D >= 10000){
                    tmp_D &= 10000;
                }
                que.add(new String[] {String.valueOf(tmp_D), now_str[1] + "D"});

                int tmp_S = tmp -1;
                if(tmp_S == 0){
                    tmp_S = 9999;
                }
                que.add(new String[] {String.valueOf(tmp_S), now_str[1] + "S"});

                String buffer = "";
                for(int i=0;i<4 - now_str[0].length();i++){
                    buffer += "0";
                }
                buffer += now_str[0];

                sb.append(Character.toString(buffer.charAt(1)));
                sb.append(Character.toString(buffer.charAt(2)));
                sb.append(Character.toString(buffer.charAt(3)));
                sb.append(Character.toString(buffer.charAt(0)));
                String tmp_L = sb.toString();
                que.add(new String[] {tmp_L, now_str[1] + "L"});
                sb.setLength(0);

                sb.append(Character.toString(buffer.charAt(3)));
                sb.append(Character.toString(buffer.charAt(0)));
                sb.append(Character.toString(buffer.charAt(1)));
                sb.append(Character.toString(buffer.charAt(2)));
                String tmp_R = sb.toString();
                que.add(new String[] {tmp_R, now_str[1] + "R"});
                sb.setLength(0);
                }
        }
        sc.close();
        return;
    }
}
