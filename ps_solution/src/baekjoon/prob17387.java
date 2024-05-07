package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob17387 {
    static StringTokenizer st = null;
    static long[][][] p = new long[2][2][2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        p[0][0][0] = Long.parseLong(st.nextToken());
        p[0][0][1] = Long.parseLong(st.nextToken());
        p[0][1][0] = Long.parseLong(st.nextToken());
        p[0][1][1] = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        p[1][0][0] = Long.parseLong(st.nextToken());
        p[1][0][1] = Long.parseLong(st.nextToken());
        p[1][1][0] = Long.parseLong(st.nextToken());
        p[1][1][1] = Long.parseLong(st.nextToken());

        int ccw1 = CCW(p[0][0], p[0][1], p[1][0]);
        int ccw2 = CCW(p[0][0], p[0][1], p[1][1]);
        int ccw3 = CCW(p[1][0], p[1][1], p[0][0]);
        int ccw4 = CCW(p[1][0], p[1][1], p[0][1]);

        boolean b1 = Math.min(p[0][0][0], p[0][1][0]) <= Math.max(p[1][0][0], p[1][1][0]);
        boolean b2 = Math.min(p[1][0][0], p[1][1][0]) <= Math.max(p[0][0][0], p[0][1][0]);
        boolean b3 = Math.min(p[0][0][1], p[0][1][1]) <= Math.max(p[1][0][1], p[1][1][1]);
        boolean b4 = Math.min(p[1][0][1], p[1][1][1]) <= Math.max(p[0][0][1], p[0][1][1]);

        int result = 0;
        boolean findFlag = false;
        // 평행
        if(ccw1 * ccw2 == 0 && ccw3 * ccw4 == 0){
            findFlag = true;
            if(b1 && b2 && b3 && b4){
                result = 1;
            }
        }
        if(ccw1 * ccw2 <= 0 && ccw3 * ccw4 <= 0){
            if(!findFlag){
                result = 1;
            }
        }
        
        System.out.println(result);
    }

    private static int CCW(long[] p1, long[] p2, long[] p3){
        long[] v1 = new long[2];
        v1[0] = p2[0] - p1[0];
        v1[1] = p2[1] - p1[1];
        
        long[] v2 = new long[2];
        v2[0] = p3[0] - p2[0];
        v2[1] = p3[1] - p2[1];

        long ccw = v1[0] * v2[1] - v1[1] * v2[0];

        if(ccw < 0){
            return -1;
        }
        if(ccw > 0){
            return 1;
        }
        return 0;
    }
}
