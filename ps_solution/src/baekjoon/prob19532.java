package baekjoon;

import java.io.*;

public class prob19532 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");

		int a, b, c, d, e, f;

		a = Integer.parseInt(s[0]);
		b = Integer.parseInt(s[1]);
		c = Integer.parseInt(s[2]);
		d = Integer.parseInt(s[3]);
		e = Integer.parseInt(s[4]);
		f = Integer.parseInt(s[5]);

		int x = 0;
		int y = 0;

		for (int i = -999; i <= 999; i++) {
			for (int j = -999; j <= 999; j++) {
				if (a * i + b * j == c && d * i + e * j == f) {
					x = i;
					y = j;
					break;
				}
			}
		}
		System.out.println(x + " " + y);
	}

}
