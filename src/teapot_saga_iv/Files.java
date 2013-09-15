package teapot_saga_iv;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;



public class Files {
    
    
    public static char map[][];
    public static String mapData[][];
    public static int mapNum = 1;
    public static int startX, startY, exitX, exitY;
    
    public static final String FILESPATH = System.getProperty("user.dir") + "/src/teapot_saga_iv/files/";
    private static final String DAT = "dat", MAP = "map";
    
    
    
    /*
     * Loads the current .dat and .map file, from a predefined location.
     */
    public static void load()
    {
        loadFromFile(MAP);
        loadFromFile(DAT);
    }
    
    /*
     * Checks weather the specified map exists.
     */
    public static boolean MapExists(int a)
    {
        File file = new File(FILESPATH + (mapNum + a) + "." + MAP);
        System.out.println(file.exists());
        return file.exists();
    }
    
    /*
     * Loads the current .dat file or .map file depending on the arguments passed, from a predefined location.
     */
    public static void loadFromFile(String name)
    {
        try {
            
            ArrayList<String> list = new ArrayList<String>();

            Scanner scanner = new Scanner(new File(FILESPATH + mapNum + "." + name));
            scanner.useDelimiter("\n");

            while (scanner.hasNext())
            {
                list.add(scanner.next());
            }
            scanner.close();
            
            if(name == MAP) {
                
                char[][] mapTemp = new char[list.size()][];
                
                for (int i = 0; i < list.size(); i++)
                {
                    mapTemp[i] = list.get(i).toCharArray();
                }
                
                map = mapTemp;
                
                for (int i = 0; i < map.length; i++) { for (int j = 0; j < map[i].length; j++) { System.out.print(map[i][j]); } System.out.println(); }
                
                parseMap();
                
            } else {    //loads the data from <mapID>.dat files
                
                String[][] mapDataTemp = new String[list.size()][];
                
                for (int i = 0; i < list.size(); i++)
                {
                    mapDataTemp[i] = list.get(i).toLowerCase().split(",");
                }
                
                mapData = mapDataTemp;
            }
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
  
    
    /**
     * Searches the current map and gets the start and exit positions in terms of X and Y
     */
    public static void parseMap()
    {
        for (int y = 0; y < map.length; y++)
        {
            for (int x = 0; x < map[0].length; x++)
            {
                if (map[y][x] == '<')
                {
                    startX = x;
                    startY = y;
                    System.out.println("Start: " + startX + " " + startY);
                } else if (map[y][x] == '>')
                {
                    exitX = x;
                    exitY = y;
                    System.out.println("Exit: " + exitX + " " + exitY);
                }
            }
        }
    }
}