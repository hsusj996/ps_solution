package baekjoon;

import java.util.*;
import java.io.*;

public class prob6603 {
    static int[] numbers;
    static boolean[] visited;
    static int k;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            k = Integer.parseInt(st.nextToken());

            if(k == 0){
                break;
            }

            numbers = new int[k];
            visited = new boolean[k];
            for(int i=0;i<k;i++){
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            bfs(0, -1);

            System.out.println();
        }
    }    

    static void bfs(int depth, int prev_index){
        if(depth == 6){
            for(int i=0;i<k;i++){
                if(visited[i]){
                    System.out.print(numbers[i] + " ");
                }
            }
            System.out.println();

            return;
        }

        for(int i=prev_index+1;i<k;i++){
            visited[i] = true;
            bfs(depth + 1, i);
            visited[i] = false;
        }
    }
}
