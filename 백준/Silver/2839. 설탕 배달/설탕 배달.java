import java.util.Scanner;
import java.io.*;

public class Main {
	static int N;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		int cnt3 = 0;

		while ((N - (3 * cnt3)) > 0 && (N - (3 * cnt3)) % 5 != 0) {
			cnt3++;
		}

		if ((N - (3 * cnt3)) % 5 != 0) {
			System.out.println(-1);
			return;
		} else {
			System.out.println(cnt3 + (N - (3 * cnt3)) / 5);
		}
	}
}