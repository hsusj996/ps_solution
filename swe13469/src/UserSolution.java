import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class UserSolution {
    static LinkedList<Character>[] memo;
    static int h, w;
    static int[][] spellCntArr;
    static int[] cursorPos;
    static int[] endPos;

    void init(int H, int W, char mStr[]) {
        memo = new LinkedList[H + 1];
        for (int i = 1; i <= H; i++) {
            memo[i] = new LinkedList<>();
        }

        spellCntArr = new int[H + 1][26];

        h = H;
        w = W;
        cursorPos = new int[] { 1, 1 };
        endPos = new int[] { 1, 1 };
        for (int i = 0; mStr[i] != '\0'; i++) {
            insert(mStr[i]);
        }

        cursorPos[0] = 1;
        cursorPos[1] = 1;
    }

    void insert(char mChar) { // 항상 삽입, 삭제시에 철자 카운트 갱신하기
        // 삽입 후 현재 커서 줄 이후의 줄 밀기
        memo[cursorPos[0]].add(cursorPos[1] - 1, mChar);
        spellCntArr[cursorPos[0]][mChar - 'a']++;
        for (int i = cursorPos[0]; i <= h; i++) {
            // 검사하는 줄이 너비를 초과했다면 끝 문자 다음줄로 넘기기
            if (memo[i].size() > w) {
                char c = memo[i].pollLast();
                spellCntArr[i][c - 'a']--;
                if (i < h) {
                    memo[i + 1].addFirst(c);
                    spellCntArr[i + 1][c - 'a']++;
                }
                continue; // 다음 줄도 검사
            }
            break; // 다음 줄 검사할 필요 없음
        }

        // 커서 갱신
        cursorPos[1]++;
        if (cursorPos[1] > w) {
            if (cursorPos[0] < h) {
                cursorPos[0]++;
                cursorPos[1] = 1;
            } else {
                cursorPos[1]--;
            }
        }

        // 끝 위치 갱신
        endPos[1]++;
        if (endPos[1] > w) {
            if (endPos[0] < h) {
                endPos[0]++;
                endPos[1] = 1;
            } else {
                endPos[1]--;
            }
        }
    }

    char moveCursor(int mRow, int mCol) {
        // 이동가능한 경우
        if (memo[mRow].size() >= mCol) {
            // 커서 갱신
            cursorPos[0] = mRow;
            cursorPos[1] = mCol;

            return memo[mRow].get(mCol - 1);
        }

        // 불가능한 경우
        // 커서 갱신
        cursorPos[0] = endPos[0];
        cursorPos[1] = endPos[1];

        return '$';
    }

    int countCharacter(char mChar) {
        int cntSum = 0;
        // 현재 커서 다음 행 카운트
        for (int i = cursorPos[0] + 1; i <= h; i++) {
            cntSum += spellCntArr[i][mChar - 'a'];
        }

        // 현재 커서 행 카운트
        for (int i = cursorPos[1] - 1; i < memo[cursorPos[0]].size(); i++) {
            if (memo[cursorPos[0]].get(i) == mChar) {
                cntSum++;
            }
        }

        return cntSum;
    }

    void PrintForDebug() {
        for (int i = 1; i <= h; i++) {
            memo[i].forEach(c -> System.out.print(c + " "));
            System.out.println();
        }
        System.out.println();
    }
}