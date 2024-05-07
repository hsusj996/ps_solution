package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob10775 {
    static int G, P;
    static int[] parents;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        parents = new int[G + 1];
        MakeSet();
        int ans = 0;
        for(int i=0;i<P;i++){
            int g = Integer.parseInt(br.readLine());
            int emptyGet = FindSet(g);

            if(emptyGet == 0){
                break;
            }

            ans++;
            Union(emptyGet, emptyGet-1);
        }

        System.out.println(ans);
    }
    private static void MakeSet() {
        for(int i=1;i<=G;i++){
            parents[i] = i;
        }
    }
    private static int FindSet(int a){
        if(parents[a] == a){
            return a;
        }

        return parents[a] = FindSet(parents[a]);
    }

    private static boolean Union(int a, int b){
        int aRoot = FindSet(a);
        int bRoot = FindSet(b);

        if(aRoot == bRoot){
            return false;
        }

        parents[aRoot] = bRoot;
        return true;
    }
}
