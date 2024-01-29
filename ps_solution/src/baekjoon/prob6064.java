package baekjoon;

import java.util.Scanner;

public class prob6064 {
    static int T;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        T = sc.nextInt();

        while(T-->0){
            int N = sc.nextInt();
            int M = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();

            System.out.println(Kain(N, M, x, y));
        }

        sc.close();
        return;
    }

    public static int Kain(int N, int M, int x, int y) {
        x -= 1;
        y -= 1;

        for(int i=x; i<(N*M);i+=N){
            if(i % M == y){
                return i + 1;
            }
        }
        return -1;
    }
}
