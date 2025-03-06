/* File reader based on the one found at
 *  https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
 * Modified for the specific purpose of the rythmflow.
 * and made a little more clear on what the variables are doing.
 */
package classes;
import java.io.*;
public class FastReader
{
    private final int BUFFER_SIZE = 1 << 16; //The size of the buffer
    private FileInputStream din; //File stream for input.
    private byte[] buffer; //An array to store bytes in
    private int bufferPointer, bytesRead; 
    
    
    public FastReader(String filename) throws FileNotFoundException{
        ClassLoader cl = getClass().getClassLoader();
        din = new FileInputStream(new File(cl.getResource("."+filename).getFile()));
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