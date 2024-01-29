package baekjoon;

import java.util.*;

public class prob3273 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        int x = sc.nextInt();

        int ans = 0;

        int p1 = 0;
        int p2 = n-1;

        for(;p1<p2;p1++){
            for(;p2>p1;p2--){
                if(arr[p1] + arr[p2] == x){
                    ans++;
                    p2--;
                    break;
                } else if(arr[p1] + arr[p2] < x){
                    break;
                }
            }
        }

        System.out.println(ans);
    }    
}
