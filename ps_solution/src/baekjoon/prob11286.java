package baekjoon;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class prob11286 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Integer> ABSHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(Math.abs(o1) == Math.abs(o2)){
                    return o1 - o2;
                }
                return Math.abs(o1) - Math.abs(o2);
            }
        });

        int N = sc.nextInt();

        for(int i=0;i<N;i++){
            int x = sc.nextInt();

            if(x == 0){
                if(ABSHeap.isEmpty()){
                    System.out.println(0);
                }else{
                    System.out.println(ABSHeap.poll());
                }
            }
            else{
                ABSHeap.add(x);
            }
        }
        sc.close();
        return;
    }
}
