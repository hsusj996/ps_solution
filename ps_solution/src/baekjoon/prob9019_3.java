package baekjoon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class prob9019_3 {
    static int T;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        while(T-->0){
            int before = sc.nextInt();
            int after = sc.nextInt();

            String[] cmd = new String[10000];
            boolean[] visited = new boolean[10000];

            Queue<Integer> que = new LinkedList<>();

            visited[before] = true;
            que.add(before);

            Arrays.fill(cmd, "");

            while(!que.isEmpty() && !visited[after]){
                int now = que.poll();

                int D = (now*2) % 10000;
                int S = (now==0) ? 9999 : now - 1;
                int L = (now % 1000) * 10 + now / 1000;
                int R = (now % 10) *1000 + now/10;

                if(!visited[D]){
                    que.add(D);
                    visited[D] = true;
                    cmd[D] = cmd[now] + "D";
                }
                
                if(!visited[S]){
                    que.add(S);
                    visited[S] = true;
                    cmd[S] = cmd[now] + "S";
                }
                
                if(!visited[L]){
                    que.add(L);
                    visited[L] = true;
                    cmd[L] = cmd[now] + "L";
                }
                
                if(!visited[R]){
                    que.add(R);
                    visited[R] = true;
                    cmd[R] = cmd[now] + "R";
                }
            }
            System.out.println(cmd[after]);
        }
        sc.close();
        return;
    }
}
