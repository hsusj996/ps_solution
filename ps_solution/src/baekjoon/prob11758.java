package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob11758 {
    static StringTokenizer st = null;
    static int[][] p = new int[3][2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0;i<3;i++){
            st = new StringTokenizer(br.readLine());

            p[i][0] = Integer.parseInt(st.nextToken());
            p[i][1] = Integer.parseInt(st.nextToken());
        }

        int v1[] = new int[2];
        v1[0] = p[1][0] - p[0][0];
        v1[1] = p[1][1] - p[0][1];

        int v2[] = new int[2];
        v2[0] = p[2][0] - p[1][0];
        v2[1] = p[2][1] - p[1][1];

        int ans = v1[0] * v2[1] - v1[1] * v2[0];

        if(ans < 0){
            ans = -1;
        }
        if(ans > 0){
            ans = 1;
        }

        System.out.println(ans);
    }
}
