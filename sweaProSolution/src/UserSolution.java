import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

class UserSolution {
    static final int[][] d = { { -1, -1, 1, 1 }, { -1, 1, 1, -1 } };

    static class Tile {
        int[] info;

        public Tile(int[] info) {
            this.info = info;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.hashCode(info);
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Tile)) {
                return false;
            }
            Tile o = (Tile) obj;

            for (int i = 0; i < 5; i++) {
                if (this.info[i] != o.info[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    static HashMap<Tile, List<int[]>> possibleTilePosMap;
    static HashMap<Integer, int[]> currentTileMap;
    static boolean[][] boardState;

    void init(int N, int mInfo[][]) {
        possibleTilePosMap = new HashMap<>();
        boardState = new boolean[N][N];
        currentTileMap = new HashMap<>();

        for (int i = 1; i < N - 1; i += 2) {
            int j = (i % 2 == 1) ? 1 : 2;
            for (; j < N - 1; j += 2) {
                // 해당 위치의 벽의 3x3 구역 분석
                int boltCnt = 0;    // 구역 내 볼트 개수
                int info[] = new int[5];    // 구역 정보
                for (int k = 0; k < 5; k++) {
                    int newX = i + d[0][k];
                    int newY = j + d[1][k];

                    info[k] = mInfo[newX][newY];
                    if (info[k] <= 5) {
                        boltCnt++;
                    }
                }

                if (boltCnt > 1) {  // 볼트가 2개 이상이면 불가능
                    continue;
                }

                Tile tp = new Tile(info);

                if (!possibleTilePosMap.containsKey(tp)) {  // 해당 모양의 타일 확인 후 없으면 원소 생성
                    possibleTilePosMap.put(tp, new ArrayList<>());
                }

                possibleTilePosMap.get(tp).add(new int[] { i, j }); // 좌표 리스트에 삽입
            }
        }
    }

    int add(int mID, int mTile[][]) {
        int[] info = new int[5];
        int nutPos = -1;    // 너트 위치 확인 뒤 해당 가능한 모양 모두 조사
        for (int k = 0; k < 5; k++) {
            int newX = 1 + d[0][k];
            int newY = 1 + d[1][k];

            if (info[k] >= 11) {    // 너트라면
                nutPos = k;
            } else {
                info[k] = mTile[newX][newY] + 10;   // 볼트인 경우에는 비교를 위해 모두 너트로 변경
            }

        }

        Queue<int[]> possiblePosQ = new ArrayDeque<>(); // 가능한 위치 후보군
        if (nutPos == -1) { // 모두 볼트라면
            List<int[]> posList = possibleTilePosMap.get(new Tile(info));   // 삽입 타일의 모양이 있는 위치 리스트
            for (int[] pos : posList) {
                boolean flag = true;
                for (int k = 0; k < 5; k++) {   // board 탐색, 주변 타일을 확인
                    if (boardState[pos[0] + d[0][k]][pos[1] + d[1][k]]) {   // 주변에 타일이 있다면
                        flag = false;
                        break;
                    }
                }

                if (flag) { // 주변 타일이 없다면 후보군 큐에 삽입
                    possiblePosQ.add(pos);
                }
            }
        } else {    // 너트가 1개 존재한다면
            int originNut = info[nutPos];   // 기존 너트 모양
            for (int i = 11; i <= 15; i++) {    // 가능한 타일 모양을 모두 탐색 (너트 모양을 바꿔가며)
                if (originNut == i) // 원래 모양은 조사 x
                    continue;
                info[nutPos] = i;

                List<int[]> posList = possibleTilePosMap.get(new Tile(info));
                for (int[] pos : posList) {
                    boolean flag = true;
                    for (int k = 0; k < 5; k++) {
                        if (boardState[pos[0] + d[0][k]][pos[1] + d[1][k]]) {
                            flag = false;
                            break;
                        }
                    }

                    if (flag) {
                        possiblePosQ.add(pos);
                    }
                }
            }
        }

        if(possiblePosQ.isEmpty()){ // 비어있다면 가능한 후보군이 없음 -> return
            return -1;
        }

        int[] pos = { 1000, 1000 }; // 가능한 후보군을 순회, 우선순위에 따른 위치 체크
        while (!possiblePosQ.isEmpty()) {
            int[] curPos = possiblePosQ.poll();

            if (curPos[0] < pos[0]) {   // row가 작을 수록
                pos = curPos;
                continue;
            } else if (curPos[0] == pos[0]) {   
                if (curPos[1] < pos[1]) { // col이 작을수록
                    pos = curPos;
                    continue;
                }
            }
        }

        for (int k = 0; k < 5; k++) {   // 타일 삽입 위치 주변 좌표 true로 설정
            boardState[pos[0] + d[0][k]][pos[1] + d[1][k]] = true;
        }
        currentTileMap.put(mID, pos);   // 타일 정보를 맵에 삽입
        return (pos[0] - 1) * 10000 + (pos[1] -1);
    }

    void delete(int mID) {
        if(!currentTileMap.containsKey(mID)){   // 해당 id의 타일이 있는지 확인
            return;
        }

        int[] pos = currentTileMap.get(mID);

        currentTileMap.remove(mID);
        for (int k = 0; k < 5; k++) {   // 타일 제거 뒤 주변 좌표 false로 설정
            boardState[pos[0] + d[0][k]][pos[1] + d[1][k]] = false;
        }
    }
}