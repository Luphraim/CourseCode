package xmu.edu.cn;
import java.io.*;
public class OutputLog {
    final ByteArrayOutputStream out=new ByteArrayOutputStream();
    public OutputLog(){
    	System.setOut(new PrintStream(out));
    }
    void captureStandardOut(){
    	System.setOut(new PrintStream(out));
    }
    void reset(){
    	System.setOut(System.out);
    }
    String getLog(){
    	return out.toString();
    }
}
