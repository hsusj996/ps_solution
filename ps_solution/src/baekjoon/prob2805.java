package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob2805 {
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s1[] = br.readLine().split(" ");

        int N = Integer.parseInt(s1[0]);
        int M = Integer.parseInt(s1[1]);

        String s2[] = br.readLine().split(" ");
        int tree[] = new int[N];

        for(int i=0;i<N;i++){
            tree[i] = Integer.parseInt(s2[i]);
        }

        int low = 0;
        int high = getMax(tree);

        while(low < high){
            int mid = (low + high)/2;
            
            int tree_len = getTree(tree, mid);

            if(tree_len < M){
                high = mid;
            }else{
                low = mid + 1;
            }
        }
        System.out.println(low - 1);
    }

    public static int getMax(int arr[]){
        int max = 0;

        for(int i=0;i<arr.length;i++){
            if(max <= arr[i]){
                max = arr[i];
            }
        }
        return max;
    }

    public static int getTree(int tree[], int target){
        int result = 0;

        for(int i=0;i<tree.length;i++){
            if((tree[i] - target) >= 0){
                result += (tree[i] - target);
            }
        }

        return result;
    }
}
