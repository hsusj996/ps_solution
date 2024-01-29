package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class prob25206 {
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        float sum_of_score = 0;
        float sum_of_hakjum = 0;

        for(int i=0;i<20;i++){
            String s[] = br.readLine().split(" ");

            float hakjum = Float.parseFloat(s[1]);
            String score = s[2];

            if(score.compareTo("P") == 0){
                continue;
            }
            else{
                sum_of_score += getSumOfScore(hakjum, score);
                sum_of_hakjum += hakjum;
            }
        }
        System.out.println(sum_of_score / sum_of_hakjum);
    }

    public static float getSumOfScore(float hakjum, String score){
        float score_value = 0;
        if(score.compareTo("A+") == 0){
            score_value = (float) 4.5;
        }
        else if(score.compareTo("A0") == 0){
            score_value = (float) 4.0;
        }
        else if(score.compareTo("B+") == 0){
            score_value = (float) 3.5;
        }
        else if(score.compareTo("B0") == 0){
            score_value = (float) 3.0;
        }
        else if(score.compareTo("C+") == 0){
            score_value = (float) 2.5;
        }
        else if(score.compareTo("C0") == 0){
            score_value = (float) 2.0;
        }
        else if(score.compareTo("D+") == 0){
            score_value = (float) 1.5;
        }
        else if(score.compareTo("D0") == 0){
            score_value = (float) 1.0;
        }
        else if(score.compareTo("F") == 0){
            score_value = (float) 0;
        }
        return hakjum * score_value;
    }
}
