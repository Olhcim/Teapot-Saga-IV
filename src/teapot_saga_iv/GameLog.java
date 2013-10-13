package teapot_saga_iv;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
//import java.io.PrintWriter;



public class GameLog {

    private final String FILESPATH = System.getProperty("user.dir") + "/src/log.txt";
    private Writer writer;
    //private PrintWriter writer;
    
    public GameLog()
    {
        try {
            writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(FILESPATH), "utf-8") );
            //writer = new PrintWriter(FILESPATH, "UTF-8");
        } catch (Exception e) {}
    }
    
    public void println(String a)
    {
        try {
            writer.write(a + "\n");
            //writer.println(a);
        } catch (IOException e) {}
    }
    
    public void print(String a)
    {
        try {
            writer.write(a);
            //writer.println(a);
        } catch (IOException e) {}
    }
    
    public void closeWriter()
    {
        try {
            writer.close();
        } catch (IOException ex) {}
    }
} 