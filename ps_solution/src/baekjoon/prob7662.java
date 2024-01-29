package baekjoon;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class prob7662 {
    static int T;

    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);

        T = sc.nextInt();

        while(T-->0){
            int k = sc.nextInt();
            PriorityQueue<Integer> min_que = new PriorityQueue<>();
            PriorityQueue<Integer> max_que = new PriorityQueue<>(Collections.reverseOrder());

            while(k-->0){
                String cmd = sc.next();
                int n = sc.nextInt();

                if(cmd.equals("I")){    //Insert
                    min_que.offer(n);
                    max_que.offer(n);
                }else{  //delete
                    if(min_que.isEmpty() && max_que.isEmpty()){
                        continue;
                    }
                    if(n == 1){
                        int remove_val = max_que.poll();
                        min_que.remove(remove_val);
                    }else{
                        int remove_val = min_que.poll();
                        max_que.remove(remove_val);
                    }
                }
            }
            if(min_que.isEmpty()){
                System.out.println("EMPTY");
            }else{
                System.out.println(max_que.peek());
                System.out.println(min_que.peek());
            }
        }
        sc.close();
        return;
    }
}
