package baekjoon;

import java.util.Scanner;

public class prob2630 {
    static int N;
    static int[][] paper;
    static int ans[] = new int[] { 0, 0 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        paper = new int[N][N]; // 종이 정보 행렬 초기화

        for (int i = 0; i < N; i++) { // 행렬 입력
            for (int j = 0; j < N; j++) {
                paper[i][j] = sc.nextInt();
            }
        }

        recursive(paper);   //재귀

        System.out.println(ans[0] + "\n" + ans[1]);
        sc.close();
        return;
    }

    public static void recursive(int[][] arr) {
        if (check(arr)) {   //행렬이 모두 같은 수인지 체크
            ans[arr[0][0]]++;   //같다면 +
            return;
        }else{  //아니라면 사분면으로 나눈 뒤 다시 재귀
            for(int i=0;i<2;i++){
                for(int j=0;j<2;j++){
                    int small_arr[][] = new int[arr.length / 2][arr.length / 2];
                    for(int n=0;n<small_arr.length;n++){
                        for(int m=0;m<small_arr.length;m++){
                            small_arr[n][m] = arr[n + (i * arr.length / 2)][m + j * arr.length / 2];
                        }
                    }
                    recursive(small_arr);
                }
            }
        }
        return;
    }

    public static boolean check(int[][] arr){
        int compare = arr[0][0];
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                if(compare != arr[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
