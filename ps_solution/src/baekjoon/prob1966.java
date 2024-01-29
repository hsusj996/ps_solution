package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class prob1966 {
    public static void main(String [] args) throws NumberFormatException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while(T --> 0){
            Queue<int[]> que = new LinkedList<>();
            String s1[] = br.readLine().split(" ");
            int N = Integer.parseInt(s1[0]);
            int M = Integer.parseInt(s1[1]);
            int ans = 0;

            String s2[] = br.readLine().split(" ");

            for(int i=0;i<N;i++){
                que.add(new int[]{i, Integer.parseInt(s2[i])});
            }

            while(true){
                int front[] = que.poll();
                boolean flag = true;

                for(int q[]: que){
                    if(q[1] > front[1]){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    ans++;
                    if(front[0] == M) break;
                }else{
                    que.add(front);
                }
            }
            System.out.println(ans);
        }
    }
}
