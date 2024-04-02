package baekjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class prob11005 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int B = sc.nextInt();

        List<Integer> list = new ArrayList<>();
        while(N > 0){
            list.add(N % B);
            N /= B;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = list.size() - 1; i >= 0; i--){
            if(list.get(i) >= 10)  {
                sb.append((char)(list.get(i) - 10 + 'A'));
            } else{
                sb.append(list.get(i));
            }
        }

        System.out.println(sb.toString());
        return;
    }
}
