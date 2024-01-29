package baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class prob11866 {
    public static void main(String [] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       String s[] = br.readLine().split(" ");

       int people = Integer.parseInt(s[0]);
       int number = Integer.parseInt(s[1]);

       Queue<Integer> q = new LinkedList<>();

       for(int i=1;i<=people;i++) {
            q.add(i);
       }

       StringBuilder sb = new StringBuilder();
       sb.append("<");

       while(q.size() > 1){
            for(int i = 0; i < number - 1; i++){
                int val = q.poll();
                q.offer(val);
            }

            sb.append(q.poll()).append(", ");

       }

       sb.append(q.poll()).append(">");
       System.out.println(sb);
    }
}
