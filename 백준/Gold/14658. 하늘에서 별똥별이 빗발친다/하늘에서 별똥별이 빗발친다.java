import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class xy implements Comparable<xy>{
        int x;
        int y;

        public xy(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(xy o) {
            if(this.x == o.x){
                return this.y - o.y;
            } else{
                return this.x - o.x;
            }
        }
    }
    static StringTokenizer st = null;
    static int N, M, L, K;
    static xy[] stars;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        stars = new xy[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i] = new xy(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(stars);
        int max = 0;

        for(int i=0;i<K;i++){
            for(int j=0;j<K;j++){
                int x1 = stars[i].x;
                int y1 = stars[j].y;

                max = Math.max(max, countStars(x1, y1));
            }
        }

        System.out.println(K - max);
    }

    private static int countStars(int x1, int y1) {
        int ret = 0;
        for(xy star:stars){
            if(star.x >= x1 && star.y >= y1 && star.x <= x1 + L && star.y <= y1 + L){
                ret++;
            }
        }

        return ret;
    }

}