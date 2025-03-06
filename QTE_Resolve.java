/* Author: Cody D. Hill
 * Rythm Flow
 * Quick Time Event Resolution
 * 
 */
import java.util.Scanner;
import java.io.*;
//for testing
import java.util.Arrays;

public class QTE_Resolve
{
    public static void main(String[] args)
    throws IOException
    {
        long startTime = System.nanoTime();
        System.out.println(ScoreResolve("1.in"));
        System.out.println(ScoreResolve("2.in"));
        System.out.println(ScoreResolve("3.in"));
        System.out.println(ScoreResolve("BigData.txt"));
        long endTime = System.nanoTime();
        double duration = (endTime - startTime)/1000;
        System.out.println("Operation took:" + duration+" microseconds");
    }
    
    public static int ScoreResolve(String FileName)
    throws IOException
    {
        //Load file(s) 
        //Scanner input = new Scanner (new File(FileName));
        FileReader input = new FileReader(FileName);
        //Try different method for reading.
        
        
        //Capture press count
        int n = input.nextInt();
        int m = input.nextInt();
        
        int[] goal_times = new int[n];
        int[][] user_times = new int[m][2];
        
        for(int line = 0; line < n+m; line++){
            //Populate Timings
            if (line < n) goal_times[line] = input.nextInt();
            else user_times[line-n][0] = input.nextInt();
        }
        

        //Map Closest Goals
        for(int uIndex = 0; uIndex < m; uIndex++){
            for (int gIndex = 0; gIndex < n; gIndex++){
                if(Math.abs(user_times[uIndex][1] - user_times[uIndex][0]) > Math.abs(goal_times[gIndex]-user_times[uIndex][0])){
                    user_times[uIndex][1] = goal_times[gIndex];
                }
            }
        }
        
        //Score
        int score = 0;
        for(int gIndex = 0; gIndex < n; gIndex++){
            int mod = 0;
            for(int uIndex = 0; uIndex < m; uIndex++){
                
                if(user_times[uIndex][1] == goal_times[gIndex]){
                    int diff = Math.abs(user_times[uIndex][1] - user_times[uIndex][0]);
                    int add = 0;
                    if(diff > 102) ;
                    else if (diff > 43) add = 2;
                    else if (diff > 23) add = 4;
                    else if (diff > 15) add = 6;
                    else add = 7;
                    if (add > mod) mod = add;
                }
                
                if(user_times[uIndex][1] > goal_times[gIndex]) break;
            }
            score += mod;
        }
        
        return score;
    }
    
    //Fast File Reader, working on understanding this.
    static class FileReader{
        private final int BUFFER_SIZE = 1 << 16; //The size of the buffer
        private FileInputStream din; //File stream for input.
        private byte[] buffer; //An array to store bytes in
        private int bufferPointer, bytesRead; 
        
        
        public FileReader(String filename) throws FileNotFoundException{
            din = new FileInputStream(filename);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = 0;
            bytesRead = 0;
        }
        
        private void fillBuffer() throws IOException{
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if(bytesRead == -1) buffer[0] = -1;
        }
        
        private byte read() throws IOException{
            if(bufferPointer == bytesRead) fillBuffer();
            return buffer[bufferPointer++];
        }
        //Next Int parser
        public int nextInt() throws IOException{
            int outInt = 0;
            byte cha = read();
            while(cha <= ' '){
                cha = read();
            }
            
            boolean neg = (cha == '-');
            if(neg) cha = read();
            do{
                outInt = outInt * 10 + cha - '0';
            } while ((cha = read()) >= '0' && cha <= '9');
            
            return neg ? -outInt : outInt;
        }
        
        
    }
}
