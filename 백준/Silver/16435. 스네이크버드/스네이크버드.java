import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int L;
	static int[] fruitArr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		fruitArr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			fruitArr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(fruitArr);
		
		for(int i=0;i<N;i++) {
			if(fruitArr[i] <= L) {
				L++;
			} else {
				break;
			}
		}
		
		System.out.println(L);
	}

}