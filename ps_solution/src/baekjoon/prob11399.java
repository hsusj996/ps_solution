package baekjoon;

import java.util.Scanner;

public class prob11399 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int atm[];
        int N = sc.nextInt();
        int sum = 0;
        int ans= 0;

        atm = new int[N];

        for(int i=0;i<N;i++){
            atm[i]= sc.nextInt();
        }

        for(int i=0;i<N;i++){   //버블정렬
            for(int j=i+1;j<N;j++){
                if(atm[i] >= atm[j]){
                    int tmp = atm[i];
                    atm[i] = atm[j];
                    atm[j]= tmp;
                }
            }
        }

        for(int i=0;i<N;i++){
            sum += atm[i];
            ans += sum;
        }

        System.out.println(ans);

        sc.close();
    }
}
