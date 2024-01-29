package baekjoon;

import java.util.Scanner;
import java.util.TreeMap;

public class prob7662_2 {
    static int T;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        while(T-->0){
            int k = sc.nextInt();
            TreeMap<Integer, Integer> que = new TreeMap<>();
            while(k-->0){
                String cmd = sc.next();
                int n = sc.nextInt();

                if(cmd.equals("I")){
                    que.put(n, que.getOrDefault(n, 0) + 1);
                }else{
                    if(que.isEmpty()){
                        continue;
                    }else{
                        int poll_value;
                        if(n == 1){
                            poll_value = que.lastKey();
                        }else{
                            poll_value = que.firstKey();
                        }

                        if(que.get(poll_value) > 1){
                            que.put(poll_value, que.get(poll_value) - 1);
                        }else{
                            que.remove(poll_value);
                        }
                    }
                }
            }
            if(que.isEmpty()){
                System.out.println("EMPTY");
            }else{
                System.out.println(que.lastKey() + " " + que.firstKey());
            }
        }

        sc.close();
        return;
    }
}
