import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int K;

	static class item {
		int weight;
		int value;

		public item(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}

	static item[] itemArr;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		itemArr = new item[N + 1];
		dp = new int[K + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			itemArr[i] = new item(w, v);
		}

		for (int i = 1; i <= N; i++) {
			for (int j = K; j >= 0; j--) {
				if (itemArr[i].weight > j) {
					continue;
				} else {
					dp[j] = Math.max(dp[j], dp[j - itemArr[i].weight] + itemArr[i].value);
				}
			}
		}

		System.out.println(dp[K]);
	}

}