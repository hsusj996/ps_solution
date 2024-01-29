package baekjoon;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class prob11279 {
    static int N;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        PriorityQueue<Integer> maxQue = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<N;i++){
            int x = sc.nextInt();

            if(x == 0){
                if(maxQue.isEmpty()){
                    System.out.println(0);
                }else{
                    System.out.println(maxQue.poll());
                }
            }
            else{
                maxQue.add(x);
            }
        }
        sc.close();
        return;
    }
}
