import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int L, C;
	static StringBuilder result = new StringBuilder();
	static char[] alphabetArr;
	static char[] selected;
	static int consonants = 0;
	static int vowels = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		alphabetArr = new char[C];
		selected = new char[L];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			alphabetArr[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(alphabetArr);

		Combination(0, 0);

		System.out.println(result);
	}

	private static void Combination(int prev, int depth) {
		if (depth == L) {
			if (consonants >= 2 && vowels >= 1) {
				for (int i = 0; i < L; i++) {
					result.append(selected[i]);
				}
				result.append('\n');
			}

			return;
		}

		for (int i = prev; i < C; i++) {
			if (alphabetArr[i] == 'a' || alphabetArr[i] == 'e' || alphabetArr[i] == 'i' || alphabetArr[i] == 'o'
					|| alphabetArr[i] == 'u') {
				vowels++;
			} else {
				consonants++;
			}

			selected[depth] = alphabetArr[i];
			Combination(i + 1, depth + 1);

			if (alphabetArr[i] == 'a' || alphabetArr[i] == 'e' || alphabetArr[i] == 'i' || alphabetArr[i] == 'o'
					|| alphabetArr[i] == 'u') {
				vowels--;
			} else {
				consonants--;
			}
		}
	}

}