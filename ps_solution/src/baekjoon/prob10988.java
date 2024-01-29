package baekjoon;

import java.util.Scanner;

public class prob10988 {
    static int ans = 1;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        for(int i=0;i<str.length();i++){
            int j = str.length() - 1 - i;

            if(str.charAt(i) != str.charAt(j)){
                ans = 0;
            }
        }

        System.out.println(ans);

        sc.close();
        return;
    }
}
