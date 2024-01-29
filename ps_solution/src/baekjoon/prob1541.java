package baekjoon;

import java.util.Scanner;

public class prob1541 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean minus = false;
        String str = sc.next();
        String buf = "";
        int ans = 0;

        for (int i = 0; i < str.length(); i++) {
            char tmp = str.charAt(i);
            if (tmp == '+') {   //+일때
                if (!minus) {   //-가 아직 나오지 않은 경우
                    ans += Integer.valueOf(buf);
                    buf = "";
                } else {
                    ans -= Integer.valueOf(buf);
                    buf = "";
                }
            } 
            else if (tmp == '-') {  //-일때
                if (!minus) {   //첫번째 -인 경우
                    ans += Integer.valueOf(buf);
                    buf = "";
                }
                else{
                    ans -= Integer.valueOf(buf);
                    buf = "";
                }
                minus = true;
            }
            else {
                buf += tmp;
            }
        }

        if (!minus) {
            ans += Integer.valueOf(buf);
            buf = "";
        } else {
            ans -= Integer.valueOf(buf);
            buf = "";
        }

        System.out.println(ans);

        sc.close();

        return;
    }
}
