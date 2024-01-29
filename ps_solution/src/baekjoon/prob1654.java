package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob1654 {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s[] = br.readLine().split(" ");

        int LAN_num = Integer.parseInt(s[0]);
        int LAN_need_num = Integer.parseInt(s[1]);
        int max = 0;

        int LAN[] = new int[LAN_num];

        for(int i=0; i<LAN.length; i++){
            LAN[i] = Integer.parseInt(br.readLine());
        }

        for(int i=0; i<LAN.length; i++){
            if(max < LAN[i]){
                max = LAN[i];
            }
        }
        
        max++;

        int min = 0;
        int mid = 0;

        while(min < max){
            mid = (min + max) / 2;
            
            int cnt = 0;

            for(int i=0;i<LAN.length;i++){
                cnt += (LAN[i]/mid);
            }

            if(cnt < LAN_need_num){
                max = mid;
            }else{
                min = mid + 1;
            }
        }
        

        
        System.out.println(min - 1);
    }
}
