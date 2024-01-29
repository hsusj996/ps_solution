package baekjoon;

import java.util.Scanner;

public class prob25314 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int N = sc.nextInt();

        int long_num = N / 4;

        for(int i=0;i<long_num;i++){
            sb.append("long ");
        }
        sb.append("int");

        System.out.println(sb.toString());

        sc.close();
        return;
    }
}
