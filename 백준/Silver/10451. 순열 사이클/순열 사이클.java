import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st = null;

    static int T;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for(int test_case =1;test_case<=T;test_case++){
            int N = Integer.parseInt(br.readLine());

            int[] arr = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for(int i=1;i<=N;i++){
                arr[i]= Integer.parseInt(st.nextToken());
            }

            boolean[] isCycled = new boolean[N + 1];
            int cnt = 0;
            for(int i=1;i<=N;i++){
                if(isCycled[i]){
                    continue;
                }

                cnt++;

                int cur = i;
                while(!isCycled[cur]){
                    isCycled[cur] = true;

                    cur = arr[cur];
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb.toString());
    }
}