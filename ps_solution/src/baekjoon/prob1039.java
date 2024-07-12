package baekjoon;

import java.util.*;

public class prob1039 {
    static class xy {
        int prev;
        int depth;
        int x;
        int y;

        public xy(int x, int y, int depth, int prev) {
            this.prev = prev;
            this.depth = depth;
            this.x = x;
            this.y = y;
        }
    }

    static class visitInfo{
        int depth;
        String n;
        public visitInfo(int depth, String n) {
            this.depth = depth;
            this.n = n;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + depth;
            result = prime * result + ((n == null) ? 0 : n.hashCode());
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof visitInfo)){
                return false;
            }

            visitInfo o = (visitInfo) obj;
            return this.depth == o.depth && this.n.equals(o.n);
        }

        
        
    }

    static String A;
    static int ANum;
    static int B;
    static int max = Integer.MIN_VALUE;
    static HashSet<visitInfo> visitedSet = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        A = sc.next();
        B = sc.nextInt();
        ANum = Integer.parseInt(A);

        for (int i = 0; i < A.length(); i++) {
            for (int j = i + 1; j < A.length(); j++) {
                bfs(new xy(i, j, 1, ANum));
            }
        }

        for(int i=1_000_000;i>=0;i--){
            if(visitedSet.contains(new visitInfo(B, String.valueOf(i)))){
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);
    }

    private static void bfs(xy start) {
        Queue<xy> q = new ArrayDeque<>();
        q.add(start);
        visitedSet.add(new visitInfo(start.depth, String.valueOf(Swap(start))));

        while (!q.isEmpty()) {
            xy cur = q.poll();
            int n = Swap(cur);
            for (int i = 0; i < A.length(); i++) {
                for (int j = i + 1; j < A.length(); j++) {
                    xy next = new xy(i, j, cur.depth + 1, n);
                    int m = Swap(next);
                    visitInfo nextInfo = new visitInfo(next.depth, String.valueOf(m));

                    if(next.depth > B || visitedSet.contains(nextInfo)){
                        continue;
                    }

                    if(next.depth == B && String.valueOf(m).length() < A.length()){
                        continue;
                    }

                    visitedSet.add(nextInfo);
                    q.add(next);
                }
            }
        }
    }

    private static int Swap(xy cur) {
        char[] origin = String.valueOf(cur.prev).toCharArray();

        char tmp = origin[cur.x];
        origin[cur.x] = origin[cur.y];
        origin[cur.y] = tmp;

        return Integer.parseInt(String.valueOf(origin));
    }

}
