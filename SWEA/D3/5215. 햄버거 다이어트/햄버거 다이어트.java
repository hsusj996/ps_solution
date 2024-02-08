import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static StringBuilder result = new StringBuilder();
	static int T;
	static int N;
	static int maxCal;
	static int[] arrCal;
	static int[] arrWorth;
	static boolean[] isSelected;
	static int maxWorth;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			maxWorth = 0;
			N = Integer.parseInt(st.nextToken());
			maxCal = Integer.parseInt(st.nextToken());
			arrCal = new int[N];
			arrWorth = new int[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				arrWorth[i] = Integer.parseInt(st.nextToken());
				arrCal[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= N; i++) {
				isSelected = new boolean[N];
				Combi(-1, 0, i);
			}

			result.append("#" + test_case + " " + maxWorth + "\n");
		}

		System.out.println(result);
	}

	private static void Combi(int start, int now, int target) {
		if (now == target) {
			int sumWorth = 0;
			int sumCal = 0;
			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					sumWorth += arrWorth[i];
					sumCal += arrCal[i];
				}
			}

			if (sumCal <= maxCal) {
				maxWorth = Math.max(maxWorth, sumWorth);
			}
			return;
		}

		for (int i = start + 1; i < N; i++) {
			isSelected[i] = true;
			Combi(i, now + 1, target);
			isSelected[i] = false;
		}
	}
}