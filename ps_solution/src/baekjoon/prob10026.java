package baekjoon;

import java.util.Scanner;

public class prob10026 {
    static int N;
    static char[][] screen; //일반 화면
    static char[][] RGscreen;   //색맹 화면
    static boolean[][] visited;
    static int[] d_row = {-1, 0, 1, 0};
    static int[] d_col = {0, -1, 0 ,1};
    static int ans[] = new int[] {0, 0};
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        screen = new char[N][N];
        RGscreen = new char[N][N];
        visited = new boolean[N][N];

        for(int i=0;i<N;i++){   //입력
            String str = sc.next();
            for(int j=0;j<N;j++){
                char tmp = str.charAt(j);

                screen[i][j] = tmp;
                if(tmp == 'R' || tmp == 'G'){   //색맹 화면은 R과 G를 구분하지 않음
                    RGscreen[i][j] = 'A';
                }
                else{
                    RGscreen[i][j] = tmp;
                }
            }
        }

        for(int i=0;i<N;i++){   //일반 화면 DFS
            for(int j=0;j<N;j++){
                if(!visited[i][j]){
                    ans[0]++;
                    visited[i][j] = true;
                    DFS(i, j);
                }
            }
        }

        visited = new boolean[N][N];    //visited 다시 초기화

        screen = RGscreen;  //색맹 화면으로 치환

        for(int i=0;i<N;i++){   //색맹 화면 DFS
            for(int j=0;j<N;j++){
                if(!visited[i][j]){
                    ans[1]++;
                    visited[i][j] = true;
                    DFS(i, j);
                }
            }
        }
        System.out.println(ans[0] + " " + ans[1]);

        sc.close();
        return;
    }
    public static void DFS(int row, int col){
        for(int i=0;i<4;i++){
            int new_row = row + d_row[i];
            int new_col = col + d_col[i];

            if(new_row >= 0 && new_row < N && new_col >=0 && new_col < N){  //새로운 좌표가 접근가능한 좌표일 때
                if(screen[new_row][new_col] == screen[row][col] && !visited[new_row][new_col]){ //이전 좌표와 같은 값이고 방문하지 않은 경우
                    visited[new_row][new_col] = true;
                    DFS(new_row, new_col);  //새 좌표 방문
                }
            }
        }
        return;
    }
}
