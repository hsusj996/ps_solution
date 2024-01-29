package baekjoon;

import java.util.Scanner;

public class prob1992 {
    static int N;
    static int quad[][];
    static StringBuilder sb;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();

        N = sc.nextInt();

        quad = new int[N][N];

        for (int i = 0; i < N; i++) {
            String str = sc.next();

            for (int j = 0; j < N; j++) {
                quad[i][j] = str.charAt(j) - '0';
            }
        }
        if (check(quad)) { 
            sb.append(quad[0][0]);
        } else {
            recursive(quad);
        }
        System.out.println(sb);

        sc.close();
        return;
    }

    public static void recursive(int arr[][]) { //재귀 분할 정복
        sb.append('(');

        for (int n = 0; n < 2; n++) {
            for (int m = 0; m < 2; m++) {
                int tmp_array[][] = new int[arr.length / 2][arr.length / 2];
                for (int j = 0; j < tmp_array.length; j++) {
                    for (int k = 0; k < tmp_array.length; k++) {    //해당 범위 배열 복사
                        tmp_array[j][k] = arr[j + arr.length * n / 2][k + arr.length * m / 2];
                    }
                }
                if (check(tmp_array)) { 
                    sb.append(tmp_array[0][0]);
                } else {    //다시 나눠야 하면 재귀
                    recursive(tmp_array);
                }
            }
        }

        sb.append(')');

        return;
    }

    public static boolean check(int arr[][]) {  //압축가능한지 확인
        int compare = arr[0][0];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != compare) {
                    return false;
                }
            }
        }
        return true;
    }
}