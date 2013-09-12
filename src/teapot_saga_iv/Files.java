package teapot_saga_iv;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;



public class Files {
    
    
    public static char map[][];
    public static String mapData[][];
    public static int mapNum = 1;
    public static int startX, startY;
    
    private static final String DAT = "dat", MAP = "map";
    
    
    
    
    public static void load()
    {
        loadFromFile(MAP);
        loadFromFile(DAT);
        parseMap();
    }
    
    
    public static void loadFromFile(String name)
    {
        try {
            
            ArrayList<String> list = new ArrayList<String>();
            
            File file = new File(System.getProperty("user.dir")
                    + "/src/teapot_saga_iv/maps/"
                    + mapNum + "." + name);

            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\n");

            while (scanner.hasNext())
            {
                list.add(scanner.next());
            }
            scanner.close();

////////////Creating char or string array based on option///////////////////////
            
            if(name==MAP) {
                
                char[][] mapTemp = new char[list.size()][];
                for (int i = 0; i < list.size(); i++)
                {
                    mapTemp[i] = list.get(i).toCharArray();
                }
                map = mapTemp;
                
                for (int i = 0; i < map.length; i++) { for (int j = 0; j < map[i].length; j++) { System.out.print(map[i][j]); } System.out.println(); }
                
            } else {    //loads the data from <mapID>.dat files
                
                String[][] mapDataTemp = new String[list.size()][];
                for (int i = 0; i < list.size(); i++)
                {
                    mapDataTemp[i] = list.get(i).toLowerCase().split(",");
                }
                mapData = mapDataTemp;
            }
////////////////////////////////////////////////////////////////////////////////
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    
    public static void parseMap()
    {
        for (int y = 0; y < map.length; y++)
        {
            for (int x = 0; x < map.length; x++)
            {
                if (map[y][x] == '<')
                {
                    startX = x;
                    startY = y;
                    
                    Player.setPos(startX, startY);
                }
            }
        }
    }
}