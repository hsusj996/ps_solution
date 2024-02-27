import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Project implements Comparable<Project> {
		int start;
		int end;

		public Project(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Project o) {
			if (this.end == o.end) {
				return o.start - this.start;
			}
			return this.end - o.end;
		}
	}

	static StringTokenizer st = null;
	static int N;
	static List<Project> pjList;
	static int pjCnt;
	static int curPjEnd;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		curPjEnd = 301;
		pjCnt = 0;
		N = Integer.parseInt(br.readLine());
		pjList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int startM = Integer.parseInt(st.nextToken());
			int startD = Integer.parseInt(st.nextToken());
			int endM = Integer.parseInt(st.nextToken());
			int endD = Integer.parseInt(st.nextToken());

			pjList.add(new Project(startM * 100 + startD, endM * 100 + endD));
		}

		Collections.sort(pjList);

		// 그리디
		while (curPjEnd <= 1130) {
			Project tmp = null;

			for (int i = pjList.size() - 1; i >= 0; i--) {
				Project pj = pjList.get(i);

				if (pj.start <= curPjEnd) {
					if (tmp == null) {
						tmp = pj;
					} else {
						if (tmp.end < pj.end) {
							pjList.remove(tmp);
							tmp = pj;
						} else {
							pjList.remove(i);
						}
					}
				}
			}

			if (tmp == null) {
				break;
			} else {
				pjCnt++;
				curPjEnd = tmp.end;
				pjList.remove(tmp);
			}
		}

		System.out.println(curPjEnd > 1130 ? pjCnt : 0);

	}
}