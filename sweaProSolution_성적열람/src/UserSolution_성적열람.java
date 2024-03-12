import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeSet;

class Student implements Comparable<Student> {
    int mId;
    int mScore;
    int mGrade;
    int mGender;

    public Student(int mId, int mScore, int mGrade, int mGender) {
        this.mId = mId;
        this.mScore = mScore;
        this.mGrade = mGrade;
        this.mGender = mGender;
    }

    @Override
    public int compareTo(Student o) {
        if (this.mScore == o.mScore) {
            return this.mId - o.mId;
        }
        return this.mScore - o.mScore;
    }
}

class UserSolution_성적열람 {
    static TreeSet<Student>[][] StudentSet;
    static HashMap<Integer, Student> StudentMap;

    public void init() {
        StudentSet = new TreeSet[2][4];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                StudentSet[i][j] = new TreeSet<>();
            }
        }

        StudentMap = new HashMap<>();
    }

    public int add(int mId, int mGrade, char mGender[], int mScore) {
        int gender = mGender[0] == 'm' ? 0 : 1;
        Student s = new Student(mId, mScore, mGrade, gender);

        StudentSet[gender][mGrade].add(s);
        StudentMap.put(mId, s);

        return StudentSet[gender][mGrade].last().mId;
    }

    public int remove(int mId) {
        if (!StudentMap.containsKey(mId)) {
            return 0;
        }

        Student s = StudentMap.get(mId);
        StudentSet[s.mGender][s.mGrade].remove(s);
        StudentMap.remove(mId);

        if (StudentSet[s.mGender][s.mGrade].size() == 0) {
            return 0;
        }

        return StudentSet[s.mGender][s.mGrade].first().mId;
    }

    public int query(int mGradeCnt, int mGrade[], int mGenderCnt, char mGender[][], int mScore) {
        PriorityQueue<Student> pq = new PriorityQueue<>();

        for (int i = 0; i < mGenderCnt; i++) {
            int gender = mGender[i][0] == 'm' ? 0 : 1;
            for (int j = 0; j < mGradeCnt; j++) {
                int grade = mGrade[j];

                Student target = StudentSet[gender][grade].higher(new Student(-1, mScore, grade, gender));
                if(target == null){
                    continue;
                }
                pq.add(target);
            }
        }

        return pq.isEmpty() ? 0 : pq.peek().mId;
    }
}