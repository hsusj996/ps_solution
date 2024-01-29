package baekjoon;

import java.util.*;
import java.io.*;

public class prob9935 {
	static String str;
	static String bomb;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		str = br.readLine();
		bomb = br.readLine();

		System.out.println(recursive(str));
	}

	static String recursive(String s) {
		StringBuilder sb = new StringBuilder();

		if (s.equals("")) {
			return "FRULA";
		}

		boolean flag = false;

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == bomb.charAt(0)) {
				if (bomb.equals(s.substring(i, i + bomb.length()))) {
					s = s.substring(0, i) + s.substring(i + bomb.length());
					i -= bomb.length();
					if (i < 0) {
						i = 0;
					}
					flag = true;
				}
			}
		}

		if (flag) {
			return recursive(s);
		} else {
			return s;
		}
	}
}
