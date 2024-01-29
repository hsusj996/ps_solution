package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class prob9019_2 {
    static int T;

    public static class node{
        int num;
        String cmd;

        public node(int num, String cmd){
            this.num = num;
            this.cmd = cmd;
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        while(T --> 0){
            String input[] = br.readLine().split(" ");

            int before = Integer.parseInt(input[0]);
            int after = Integer.parseInt(input[1]);

            Queue<node> que = new LinkedList<>(); 

            que.add(new node(before, ""));

            while(!que.isEmpty()){
                node now = que.poll();

                if(now.num == after){
                    System.out.println(now.cmd);
                    break;
                }
                int D = now.num;
                int S = now.num;
                int L = now.num;
                int R = now.num;

                D = (D * 2) % 10000;
                
                que.add(new node(D, now.cmd + "D"));

                S -= 1;
                if(S < 0){
                    S = 9999;
                }
                que.add(new node(S, now.cmd + "S"));

                L = (now.num % 1000) * 10 + now.num / 1000;
                que.add(new node(L, now.cmd + "L"));
                R = (now.num % 10) * 1000 + now.num / 10;
                que.add(new node(R, now.cmd + "R"));
            }
        }


        
    }
}
