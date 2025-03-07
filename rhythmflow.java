import java.io.*;
import java.util.Scanner;

public class rhythmflow
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        //Capture press count
        int n = input.nextInt();
        int m = input.nextInt();

        int[] goal_times = new int[n];
        int[] user_times = new int[m];

        for(int line = 0; line < n+m; line++)
        {
            //Populate Timings
            if (line < n)
            { 
                goal_times[line] = input.nextInt();
            } 
            else
            {
                user_times[line-n] = input.nextInt();  
            } 
        }

        int[][] scoreCalcs = new int[n+1][m+1];
        for(int eIndex = 1; eIndex < n+1; eIndex++){
            int mod = 0;
            for(int aIndex = 1; aIndex < m+1; aIndex++){
                scoreCalcs[eIndex][aIndex] = Math.max(scoreCalcs[eIndex][aIndex-1], scoreCalcs[eIndex-1][aIndex]);
                int a = user_times[aIndex-1];
                int e = goal_times[eIndex-1];
                //Resolve score
                if ( !(Math.abs(e-a) > 102 || Math.abs(e-a) < 0) ){
                    int diff = Math.abs(e-a);
                    if( diff > 43) mod = 2;
                    else if (diff > 23) mod = 4;
                    else if (diff > 15) mod = 6;
                    else mod = 7;
                    
                    scoreCalcs[eIndex][aIndex] = Math.max(scoreCalcs[eIndex-1][aIndex-1] + mod, scoreCalcs[eIndex][aIndex]);
                }
            }
        }

        int score = scoreCalcs[n][m];
        
        System.out.print(score);
    }
}