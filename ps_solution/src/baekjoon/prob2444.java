package baekjoon;

import java.util.Scanner;

public class prob2444 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        for (int i = 0; i < N - 1; i++) {
            int star = 2 * i + 1;

            for(int j=i;j<N-1;j++){
                System.out.print(" ");
            }
            for(int k=0;k<star;k++){
                System.out.print("*");
            }
            System.out.println();
        }

        for (int i = 0; i < 2 * N - 1; i++) {
            System.out.print("*");
        }
        System.out.println();

        for (int i = N - 2; i >= 0; i--) {
            int star = 2 * i + 1;

            for(int j=i;j<N-1;j++){
                System.out.print(" ");
            }
            for(int k=0;k<star;k++){
                System.out.print("*");
            }
            System.out.println();
        }
        sc.close();
        return;
    }
}
