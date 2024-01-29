package baekjoon;

import java.util.ArrayList;
import java.util.Scanner;

public class prob1043 {
    static class party {
        boolean cantLie;
        ArrayList<Integer> participants;

        public party(boolean cantLie, ArrayList<Integer> participants) {
            this.cantLie = cantLie;
            this.participants = participants;
        }
    }

    static int N;
    static int M;
    static int ans = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        boolean[] know_truth = new boolean[N + 1];
        party[] party_list = new party[M];

        int know_truth_cnt = sc.nextInt();

        while (know_truth_cnt-- > 0) {
            int num = sc.nextInt();

            know_truth[num] = true;
        }

        for (int i = 0; i < M; i++) {
            int participants_cnt = sc.nextInt();

            ArrayList<Integer> tmp = new ArrayList<>();
            while (participants_cnt-- > 0) {
                int num = sc.nextInt();

                tmp.add(num);
            }
            party_list[i] = new party(false, tmp);
        }

        boolean flag = true;

        while (true) {
            if(!flag){
                break;
            }

            flag = false;

            for(int i=0;i<M;i++){
                for(int j=0;j<know_truth.length;j++){
                    if(know_truth[j]){
                        if(party_list[i].participants.contains(j)){
                            party_list[i].cantLie = true;
                            for(int k=0;k<party_list[i].participants.size();k++){
                                if(!know_truth[party_list[i].participants.get(k)]){
                                    know_truth[party_list[i].participants.get(k)] = true;
                                    flag = true;
                                }   
                            }
                        }
                    }
                }
            }
        }

        for(int i=0;i<party_list.length;i++){
            if(!party_list[i].cantLie){
                ans++;
            }
        }

        System.out.println(ans);

        sc.close();
        return;
    }
}
