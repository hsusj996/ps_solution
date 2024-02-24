import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st = null;
    static int N;
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                list.add(Integer.parseInt(st.nextToken()));
            }
        }

        list.sort((o1, o2) -> o2 - o1);

        System.out.println(list.get(N - 1));
    }
}