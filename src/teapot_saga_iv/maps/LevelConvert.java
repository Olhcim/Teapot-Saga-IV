package teapot_saga_iv.maps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

//Scroll down for information.


public class LevelConvert
{
    
    public static String mapName = "1";
    public static String[][] map;
    
    public LevelConvert()
    {   
        loadMap(mapName);
    }
    
    public LevelConvert(String a)
    {   
        loadMap(a);
    }
    
    public static void loadMap(String name)
    {
        try {
            
            File file = new File(System.getProperty("user.dir")
                    + "/src/com/olhcim/teapot_saga_iv/maps/"
                    + name + ".txt");
        
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            
            String line = br.readLine();  {}
            
            String map[][];
            int x = 0, y = 0;
        
            while (line != null)
            {
                sb.append(line);
                line = br.readLine();
                sb.append('\n');
            }
        
            line = sb.toString();
            br.close();
            
            Character chars[] = new Character[line.length()];
            
            System.out.print("\n\nOrigional:\n\n");
            
            for(int i = 0; i < line.length()-1; i++)
            {chars[i] = line.charAt(i);
            System.out.print(chars[i]);}
            
            System.out.print("\n\nConverted:\n\n");

            line = "{\"";
            for(int i = 0; i < chars.length-2; i++)
            {
                if((chars[i] != '\n') & (chars[i+1] == '\n'))
                {
                    line += chars[i] + "\\n\"}," + chars[i+1] + "{\"";
                    i++;
                    
                } else {
                    line += chars[i] + "\",\"";
                }

            } line += chars[chars.length-2] + "\"}";
            
            
            System.out.println(line);

            br.close();
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main (String args[])
    {
        
    LevelConvert level = new LevelConvert(); //<< string (without .txt goes in brackets in qoutation marks 
    
    /* convert a file into format usable with Arrays.
     * Default file name is "1.txt" in the maps folder
     * 
     * pass a different value through to use a different file.
     * eg:  "2" for 2.txt
     *      "3" for 3.txt
     */
    
    }
} 