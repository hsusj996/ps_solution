package baekjoon;

import java.util.Scanner;

public class prob10798 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        String[] str_arr = new String[5];

        for(int i=0;i<5;i++){
            str_arr[i] = sc.next();
        }

        for(int i=0;i<15;i++){
            for(int j=0;j<5;j++){
                if(str_arr[j].length() > i){
                    sb.append(str_arr[j].charAt(i));
                }
            }
        }

        System.out.println(sb.toString());

        sc.close();
        return;
    }
}
