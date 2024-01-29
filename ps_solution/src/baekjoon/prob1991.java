package baekjoon;

import java.io.*;
import java.util.*;

public class prob1991 {
    static StringBuilder sb;
    static ArrayList<node> tree = new ArrayList<>();
    static int N;

    static class node {
        String now;
        String l_child;
        String r_child;
        boolean visited;

        public node(String now, String l_child, String r_child) {
            this.now = now;
            this.l_child = l_child;
            this.r_child = r_child;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> q = new LinkedList<>();

        N = Integer.parseInt(br.readLine());
        int root = 1;

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");

            if (input[0].equals("A")) {
                root = i;
            }

            tree.add(new node(input[0], input[1], input[2]));
        }

        sb = new StringBuilder();
        prefix(root);
        for (int i = 0; i < tree.size(); i++) {
            tree.get(i).visited = false;
        }
        System.out.println(sb.toString());

        sb = new StringBuilder();
        infix(root);
        for (int i = 0; i < tree.size(); i++) {
            tree.get(i).visited = false;
        }
        System.out.println(sb.toString());

        sb = new StringBuilder();
        postfix(root);
        for (int i = 0; i < tree.size(); i++) {
        tree.get(i).visited = false;
        }
        System.out.println(sb.toString());
    }

    static void prefix(int node_pos) {
        node cur_node = tree.get(node_pos);

        if (cur_node.visited) {
            return;
        }
        cur_node.visited = true;
        sb.append(cur_node.now);

        for (int i = 0; i < tree.size(); i++) {
            if (tree.get(i).now.equals(cur_node.l_child)) {
                prefix(i);
            }
        }

        for (int i = 0; i < tree.size(); i++) {
            if (tree.get(i).now.equals(cur_node.r_child)) {
                prefix(i);
            }
        }
    }

    static void infix(int node_pos) {
        node cur_node = tree.get(node_pos);

        if (cur_node.visited) {
            return;
        }

        for (int i = 0; i < tree.size(); i++) {
            if (tree.get(i).now.equals(cur_node.l_child)) {
                infix(i);
            }
        }

        cur_node.visited = true;
        sb.append(cur_node.now);

        for (int i = 0; i < tree.size(); i++) {
            if (tree.get(i).now.equals(cur_node.r_child)) {
                infix(i);
            }
        }
    }

    static void postfix(int node_pos) {
        node cur_node = tree.get(node_pos);

        if (cur_node.visited) {
            return;
        }

        for (int i = 0; i < tree.size(); i++) {
            if (tree.get(i).now.equals(cur_node.l_child)) {
                postfix(i);
            }
        }

        for (int i = 0; i < tree.size(); i++) {
            if (tree.get(i).now.equals(cur_node.r_child)) {
                postfix(i);
            }
        }

        cur_node.visited = true;
        sb.append(cur_node.now);
    }
}
