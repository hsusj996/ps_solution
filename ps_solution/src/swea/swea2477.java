package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class swea2477 {
    static final int INF = 1_000_000;
    static final int NON_ARRIVE = 0;
    static final int WAITING_RECEPTION = 1;
    static final int RECEPTION = 2;
    static final int WAITING_REPAIR = 3;
    static final int REPAIR = 4;
    static final int FINISHED = 5;

    static class Desk {
        int takenTime;
        int currentCustomerNum;
        int finishedTime;

        public Desk(int takenTime) {
            this.takenTime = takenTime;
            this.currentCustomerNum = 0;
            this.finishedTime = -1;
        }
    }

    static class Customer {
        int num;
        int arriveTime;
        int pos;
        int usedReceptionDesk;
        int usedRepairDesk;
        int receptionFinishedTime;

        public Customer(int num, int arriveTime) {
            this.num = num;
            this.pos = NON_ARRIVE;
            this.arriveTime = arriveTime;
            this.usedReceptionDesk = 0;
            this.usedRepairDesk = 0;
            this.receptionFinishedTime = INF;
        }
    }

    static StringBuilder result = new StringBuilder();
    static StringTokenizer st = null;
    static int T, N, M, K, A, B;
    static Desk[] repairDesks;
    static Desk[] receptionDesks;
    static Customer[] customers;
    static int currentCustomersCnt;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            result.append("#").append(test_case).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            customers = new Customer[K + 1];
            receptionDesks = new Desk[N + 1];
            repairDesks = new Desk[M + 1];
            currentCustomersCnt = K;

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int t = Integer.parseInt(st.nextToken());
                receptionDesks[i] = new Desk(t);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                int t = Integer.parseInt(st.nextToken());
                repairDesks[i] = new Desk(t);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                int t = Integer.parseInt(st.nextToken());
                customers[i] = new Customer(i, t);
            }

            Simulation();

            int sum = 0;
            for (int i = 1; i <= K; i++) {
                if (customers[i].usedReceptionDesk == A && customers[i].usedRepairDesk == B) {
                    sum += i;
                }
            }

            if(sum == 0){
                sum  = -1;
            }
            result.append(sum).append("\n");
        }
        System.out.println(result);

    }

    private static void Simulation() {
        PriorityQueue<Customer> waitingReceptionQ = new PriorityQueue<>((Customer o1, Customer o2) -> o1.num - o2.num);
        PriorityQueue<Customer> waitingRepairQ = new PriorityQueue<>(new Comparator<Customer>() {

            @Override
            public int compare(Customer o1, Customer o2) {
                if (o1.receptionFinishedTime == o2.receptionFinishedTime) {
                    return o1.usedReceptionDesk - o2.usedReceptionDesk;
                }
                return o1.receptionFinishedTime - o2.receptionFinishedTime;
            }
        });

        int t = 0;
        while (currentCustomersCnt > 0) {
            // 도착처리
            for (int i = 1; i <= K; i++) {
                if (customers[i].arriveTime == t) {
                    customers[i].pos = WAITING_RECEPTION;
                    waitingReceptionQ.add(customers[i]);
                }
            }
            // DESK 확인
            for (int i = 1; i <= N; i++) {
                Desk cur = receptionDesks[i];
                if (cur.finishedTime == t) {
                    customers[cur.currentCustomerNum].pos = WAITING_REPAIR;
                    customers[cur.currentCustomerNum].receptionFinishedTime = t;
                    waitingRepairQ.add(customers[cur.currentCustomerNum]);
                    cur.currentCustomerNum = 0;
                }
            }

            for (int i = 1; i <= M; i++) {
                Desk cur = repairDesks[i];
                if (cur.finishedTime == t) {
                    customers[cur.currentCustomerNum].pos = FINISHED;
                    currentCustomersCnt--;
                    cur.currentCustomerNum = 0;
                }
            }

            // DESK 갱신
            for (int i = 1; i <= N; i++) {
                Desk cur = receptionDesks[i];
                if (cur.currentCustomerNum == 0 && !waitingReceptionQ.isEmpty()) {
                    Customer customer = waitingReceptionQ.poll();
                    customer.pos = RECEPTION;
                    customer.usedReceptionDesk = i;
                    cur.currentCustomerNum = customer.num;
                    cur.finishedTime = t + cur.takenTime;
                }
            }

            for (int i = 1; i <= M; i++) {
                Desk cur = repairDesks[i];
                if (cur.currentCustomerNum == 0 && !waitingRepairQ.isEmpty()) {
                    Customer customer = waitingRepairQ.poll();
                    customer.pos = REPAIR;
                    customer.usedRepairDesk = i;
                    cur.currentCustomerNum = customer.num;
                    cur.finishedTime = t + cur.takenTime;
                }
            }

            t++;
        }
    }
}
