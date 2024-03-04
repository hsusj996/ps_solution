package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class prob21939 {
    static class Problem{
        int num;
        int level;
        public Problem(int num, int level) {
            this.num = num;
            this.level = level;
        }
    }
    
    static StringBuilder result = new StringBuilder();
    static StringTokenizer st = null;
    static int N, M;
    static TreeSet<Problem> problemAscSet;
    static TreeSet<Problem> problemDescSet;
    static Map<Integer, Problem> problemMap;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        problemDescSet = new TreeSet<>(new Comparator<Problem>() {
            @Override
            public int compare(Problem o1, Problem o2) {
                if(o1.level == o2.level){
                    return o2.num - o1.num;
                }
                return o2.level - o1.level;
            }
        });

        problemAscSet = new TreeSet<>(new Comparator<Problem>() {
            @Override
            public int compare(Problem o1, Problem o2) {
                if(o1.level == o2.level){
                    return o1.num - o2.num;
                }
                return o1.level - o2.level;
            }
        });

        problemMap = new HashMap<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());

            Problem p = new Problem(num, level);
            problemAscSet.add(p);
            problemDescSet.add(p);
            problemMap.put(num, p);
        }

        M = Integer.parseInt(br.readLine());
        while(M-->0){
            st = new StringTokenizer(br.readLine());

            String s = st.nextToken();

            if(s.equals("add")){
                int num = Integer.parseInt(st.nextToken());
                int level = Integer.parseInt(st.nextToken());
                Problem p = new Problem(num, level);
                problemAscSet.add(p);
                problemDescSet.add(p);
                problemMap.put(num, p);
            }
            if(s.equals("recommend")){
                int option = Integer.parseInt(st.nextToken());
                if(option == 1){
                    Iterator<Problem> iter = problemDescSet.iterator();
                    result.append(iter.next().num).append("\n");
                } else{
                    Iterator<Problem> iter = problemAscSet.iterator();
                    result.append(iter.next().num).append("\n");
                }
            }
            if(s.equals("solved")){
                int num = Integer.parseInt(st.nextToken());
                Problem p = problemMap.get(num);
                problemAscSet.remove(p);
                problemDescSet.remove(p);
                problemMap.remove(num);
            }
        }

        System.out.println(result);
    }
}
