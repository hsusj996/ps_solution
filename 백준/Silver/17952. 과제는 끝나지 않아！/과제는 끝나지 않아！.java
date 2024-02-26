import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static class Job {
		int A;
		int T;

		public Job(int a, int t) {
			super();
			A = a;
			T = t;
		}

	}

	static StringTokenizer st = null;
	static int N;
	static Stack<Job> stk;
	static int sum;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		stk = new Stack<>();
		sum = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());

			if (v == 1) {
				int A = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());

				stk.push(new Job(A, T));
			}

			if (!stk.isEmpty()) {
				Job j = stk.peek();
				if (--j.T == 0) {
					sum += j.A;
					stk.pop();
				}
			}
		}
		
		System.out.println(sum);
	}
}