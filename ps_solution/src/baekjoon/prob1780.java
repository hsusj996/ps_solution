package baekjoon;

import java.util.Scanner;

public class prob1780 {
    static int N;
    static int num_plus;
    static int num_minus;
    static int num_zero;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        int array[][] = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                array[i][j] = sc.nextInt();
            }
        }

        num_plus = 0;
        num_minus = 0;
        num_zero = 0;

        recursive(array);

        System.out.println(num_minus);
        System.out.println(num_zero);
        System.out.println(num_plus);
        sc.close();
        return;
    }

    public static void recursive(int array[][]) {   //재귀
        if (check(array)) {     //행렬의 수가 같은지 체크
            if (array[0][0] == 0) {
                num_zero++;
            } else if (array[0][0] == -1) {
                num_minus++;
            } else if (array[0][0] == 1) {
                num_plus++;
            }
            return;
        }

        int tmp_array[][] = new int[array.length / 3][array.length / 3];    //행렬 분할

        for (int n = 0; n < 3; n++) {   //행렬을 분할하여 작은 행렬을 재귀
            for (int m = 0; m < 3; m++) {
                for (int j = 0; j < tmp_array.length; j++) {
                    for (int k = 0; k < tmp_array.length; k++) {
                        tmp_array[j][k] = array[j + array.length * n / 3][k + array.length * m / 3];
                    }
                }
                recursive(tmp_array);
            }
        }

    }

    public static boolean check(int array[][]) {    //행렬 체크
        int tmp = array[0][0];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != tmp) {
                    return false;
                }
            }
        }
        return true;
    }
}
