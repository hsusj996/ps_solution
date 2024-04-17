package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class prob2143_2 {
  static StringTokenizer st = null;
  static int T;
  static int n, m;
  static int[] arr1;
  static int[] arr2;
  static long cnt = 0;
  static TreeMap<Integer, Long> map1 = new TreeMap<>();
  static TreeMap<Integer, Long> map2 = new TreeMap<>();

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());

    n = Integer.parseInt(br.readLine());
    arr1 = new int[n + 1];
    st = new StringTokenizer(br.readLine());
    arr1[0] = 0;
    for (int i = 1; i <= n; i++) {
      arr1[i] = arr1[i - 1] + Integer.parseInt(st.nextToken());
    }

    m = Integer.parseInt(br.readLine());
    arr2 = new int[m + 1];
    st = new StringTokenizer(br.readLine());
    arr2[0] = 0;
    for (int i = 1; i <= m; i++) {
      arr2[i] = arr2[i - 1] + Integer.parseInt(st.nextToken());
    }

    for (int p2 = 0; p2 <= n; p2++) {
      for (int p1 = 0; p1 < p2; p1++) {
        int v = arr1[p2] - arr1[p1];
        if (map1.containsKey(v)) {
          map1.replace(v, map1.get(v) + 1);
        } else {
          map1.put(v, (long)1);
        }
      }
    }

    for (int p2 = 0; p2 <= m; p2++) {
      for (int p1 = 0; p1 < p2; p1++) {
        int v = arr2[p2] - arr2[p1];
        if (map2.containsKey(v)) {
          map2.replace(v, map2.get(v) + 1);
        } else {
          map2.put(v, (long)1);
        }
      }
    }

    for(Iterator<Integer> iter = map1.keySet().iterator();iter.hasNext();){
      int v1 = iter.next();
      int v2 = T - v1;

      if(map2.containsKey(v2)){
        cnt += (map1.get(v1) * map2.get(v2));
      }
    }

    System.out.println(cnt);
  }
}
