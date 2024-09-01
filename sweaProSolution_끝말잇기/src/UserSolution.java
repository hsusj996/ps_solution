import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.HashMap;

class UserSolution {
    // static class Cycle {
    // int[] prev, nxt;
    // int n, cur;

    // public Cycle(int n) {
    // this.n = n;
    // prev = new int[n + 1];
    // nxt = new int[n + 1];
    // for (int i = 1; i <= n; i++) {
    // prev[i] = i - 1;
    // if (prev[i] == 0)
    // prev[i] = n;
    // nxt[i] = i + 1;
    // if (nxt[i] == n + 1)
    // nxt[i] = 1;
    // }
    // }

    // public void setCur(int id) {
    // cur = id;
    // }

    // public void move() {
    // cur = nxt[cur];
    // }

    // public int pop() {
    // nxt[prev[cur]] = nxt[cur];
    // prev[nxt[cur]] = prev[cur];
    // return cur;
    // }
    // }

    Queue<Integer> player;
    TreeSet<String>[] gameWordSet;
    // Cycle cycle;
    HashSet<String> selectedSet;
    int n;
    int m;

    public String getWord(char[] word) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; word[i] != '\0'; i++) {
            sb.append(word[i]);
        }
        return sb.toString();
    }

    public String getReverse(String word) {
        StringBuilder sb = new StringBuilder(word);
        return sb.reverse().toString();
    }

    public void init(int N, int M, char[][] mWords) {
        selectedSet = new HashSet<>();
        gameWordSet = new TreeSet[26];
        player = new ArrayDeque<>();
        for (int i = 0; i < 26; i++) {
            gameWordSet[i] = new TreeSet<>();
        }

        n = N;
        m = M;

        for (int i = 1; i <= N; i++) {
            player.add(i);
        }

        // cycle = new Cycle(n);

        for (int i = 0; i < M; i++) {
            String word = getWord(mWords[i]);
            gameWordSet[word.charAt(0) - 'a'].add(word);
        }
    }

    public int playRound(int mID, char mCh) {
        // cycle.setCur(mID);
        Queue<String> tmpQ = new ArrayDeque<>();

        while (player.peek() != mID) {
            player.add(player.poll());
        }

        char curAlphabet = mCh;
        while (true) {
            int playerNumber = player.poll();
            if (gameWordSet[curAlphabet - 'a'].size() > 0) {
                String s = gameWordSet[curAlphabet - 'a'].pollFirst();
                selectedSet.add(s);
                String sRev = getReverse(s);
                curAlphabet = sRev.charAt(0);
                tmpQ.add(sRev);
                player.add(playerNumber);
            } else {
                while (!tmpQ.isEmpty()) {
                    String s = tmpQ.poll();
                    if (selectedSet.contains(s)) {
                        continue;
                    }
                    gameWordSet[s.charAt(0) - 'a'].add(s);
                }
                // return cycle.pop();
                return playerNumber;
            }
            // cycle.move();
        }
    }
}