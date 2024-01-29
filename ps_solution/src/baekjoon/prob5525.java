package baekjoon;

import java.util.Scanner;

public class prob5525 {
    static int N;
    static int M;
    static String S;
    static int ans;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder("I");
        N = sc.nextInt();
        M = sc.nextInt();
        S = sc.next();

        for(int i=0;i<N;i++){
            sb.append("OI");
        }
        String target = sb.toString();
        checkString(target);

        System.out.println(ans);
        sc.close();
        return;
    }
    public static void checkString(String target){
        for(int i=0;i<M;i++){
            if(S.charAt(i) == 'I'){
                if(i + target.length() > M){
                    break;
                }
                else if(S.substring(i, i + target.length()).compareTo(target) == 0){
                    ans++;
                }
            }
        }
        return;
    }
}
