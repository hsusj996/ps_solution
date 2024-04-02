package baekjoon;

import java.util.Scanner;

public class prob1735 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a[] = new int[2];
        int b[] = new int[2];

        a[0] = sc.nextInt();
        a[1]= sc.nextInt();
        b[0]=sc.nextInt();
        b[1]=sc.nextInt();

        int bunmo = a[1] * b[1];
        int bunja = a[0] * b[1] + b[0] *a[1];

        int gcd = GCD(bunmo, bunja);

        System.out.println(bunja / gcd + " " + bunmo/gcd);
    }

    private static int GCD(int a, int b) {
        if(a<b){
            int tmp = a;
            a = b;
            b= tmp;
        }

        while(a % b != 0){
            int rem = a % b;
            a = b;
            b = rem;
        }

        return b;
    }
}
