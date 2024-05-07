package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prob9938 {
    static StringTokenizer st = null;
    static StringBuilder sb = new StringBuilder();
    static int N, L;
    static int[] parents;
    static boolean[] stateArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L =Integer.parseInt(st.nextToken());

        stateArr = new boolean[L + 1];
        parents = new int[L + 1];
        MakeSet();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            if(!stateArr[a]){
                stateArr[a] = true;
                Union(a, b);
            } else if(!stateArr[b]){
                stateArr[b] = true;
                Union(b, a);
            } else if(!stateArr[FindSet(a)]){
                stateArr[FindSet(a)] = true;
                Union(a, b);
            } else if(!stateArr[FindSet(b)]){
                stateArr[FindSet(b)]= true;
                Union(b, a);
            } else{
                sb.append("SMECE");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void MakeSet(){
        for(int i=1;i<=L;i++){
            parents[i] = i;
        }
    }

    private static int FindSet(int a){
        if(parents[a] == a){
            return a;
        }

        return parents[a] = FindSet(parents[a]);
    }

    private static void Union(int a, int b){
        int aRoot = FindSet(a);
        int bRoot = FindSet(b);

        parents[aRoot] = bRoot;
        sb.append("LADICA");
    }
}
