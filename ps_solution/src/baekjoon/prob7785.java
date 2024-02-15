package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import javax.swing.text.html.HTMLDocument.Iterator;

public class prob7785 {
    static int N;
    static StringBuilder result = new StringBuilder();
    static Set<String> enterSet = new TreeSet<>(new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o2.compareTo(o1);
        }
    });

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String cmd = st.nextToken();

            if (cmd.equals("enter")) {
                enterSet.add(name);
            } else if (cmd.equals("leave")) {
                enterSet.remove(name);
            }
        }
        enterSet.forEach(s -> result.append(s + "\n"));

        System.out.println(result);
    }
}
