import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static Edge[] edgeArr;
    static int[] parents;
    static int V, E;
    static int weightSum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parents = new int[V + 1];
        edgeArr = new Edge[E];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            edgeArr[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(edgeArr, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        MakeSet();

        int cnt = 0;
        for (int i = 0; i < E; i++) {
            Edge cur = edgeArr[i];

            if (Union(cur.from, cur.to)) {
                weightSum += cur.weight;
                cnt++;
            }

            if (cnt == E - 1) {
                break;
            }
        }

        System.out.println(weightSum);
    }

    private static void MakeSet() {
        for (int i = 0; i <= V; i++) {
            parents[i] = i;
        }
    }

    private static int FindSet(int a) {
        if (parents[a] == a) {
            return a;
        }

        return parents[a] = FindSet(parents[a]);
    }

    private static boolean Union(int a, int b) {
        int aRoot = FindSet(a);
        int bRoot = FindSet(b);

        if (aRoot == bRoot) {
            return false;
        }

        parents[bRoot] = aRoot;
        return true;
    }
}