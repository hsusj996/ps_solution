package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob1012 {
    static boolean map[][];
    static int N = 0;
    static int M = 0;
    static int K = 0;
    static int cnt = 0;

    public static void main(String []args) throws NumberFormatException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        int T = Integer.parseInt(br.readLine());
           //벌레 필요 수

        while(T -->0){
            cnt = 0;
            String s1[] = br.readLine().split(" ");

            N = Integer.parseInt(s1[0]);
            M = Integer.parseInt(s1[1]);
            K = Integer.parseInt(s1[2]);

            map = new boolean[N][M];

            for(int i=0;i<K;i++){
                String s2[] = br.readLine().split(" ");

                map[Integer.parseInt(s2[0])][Integer.parseInt(s2[1])] = true;
            }

            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(map[i][j]){
                        cnt++;
                        map[i][j] = false;
                        deleteNearbyCabbage(i, j);
                    }
                }
            }
            System.out.println(cnt);
        }
    }
    public static void deleteNearbyCabbage(int row, int col){
        int left = col - 1;
        int right = col + 1;
        int upside = row - 1;
        int downside = row + 1;

        if(left >=0 && map[row][left]){
            map[row][left] = false;
            deleteNearbyCabbage(row, left);
        }
        
        if(right < M && map[row][right]){
            map[row][right] = false;
            deleteNearbyCabbage(row, right);
        }
        
        if(upside >=0 && map[upside][col]){
            map[upside][left] = false;
            deleteNearbyCabbage(row, col);
        }
        
        if(downside < N && map[downside][col]){
            map[downside][col] = false;
            deleteNearbyCabbage(downside, col);
        }
    }

}
