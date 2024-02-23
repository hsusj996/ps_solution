import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st = null;
	static int N, d, k, c;
	static int[] sushiArr;
	static int[] sushiCnt;
	static int cnt = 0;
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		sushiArr = new int[N];
		sushiCnt = new int[d + 1];

		for (int i = 0; i < N; i++) {
			sushiArr[i] = Integer.parseInt(br.readLine());
		}

		int p1 = 0;
		int p2 = 0;

		for (int i = 0; i < k; i++) {
			if (sushiCnt[sushiArr[i]] == 0) {
				cnt++;
			}

			sushiCnt[sushiArr[i]]++;
			p2++;
		}

		int M = N;
		while (max < k + 1 && M-- > 0) {
			if (sushiCnt[c] == 0) {
				max = Math.max(max, cnt + 1);
			}
			max = Math.max(max, cnt);

			sushiCnt[sushiArr[p1]]--;
			if (sushiCnt[sushiArr[p1]] == 0) {
				cnt--;
			}

			sushiCnt[sushiArr[p2]]++;
			if (sushiCnt[sushiArr[p2]] == 1) {
				cnt++;
			}

			p1++;
			p2++;

			if (p1 == N) {
				p1 = 0;
			}
			if (p2 == N) {
				p2 = 0;
			}
		}

		System.out.println(max);
	}
}