package teapot_saga_iv;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;



public class Files {
    
    
    public static char map[][];
    public static char disMap[][];

    public static String mapData[][];
    public static int mapNum = 0;
    public static int startX, startY, exitX, exitY;
    public static String dialogStart, dialogExit;
    
    public static final String FILESPATH = System.getProperty("user.dir") + "/src/teapot_saga_iv/files/";
    private static final String DAT = "dat", MAP = "map";
    
////////////////////////////////////////////////////////////////////////////////
// MAP EXISTS
////////////////////////////////////////////////////////////////////////////////
    
    /*
     * Loads the current .dat and .map file, from a predefined location.
     */
    public static void load()
    {
        loadMapFromFile();
        parseMap();
        renderDispMap();
        
        loadDataFromFile();
        parseData();
    }

////////////////////////////////////////////////////////////////////////////////
// MAP EXISTS
////////////////////////////////////////////////////////////////////////////////
    
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
     * Checks weather the specified file exists inside the Files filder.
     */
    public static boolean MapExists(String a)
    {
        File file = new File(FILESPATH + a);
        System.out.println(file.exists());
        return file.exists();
    }
    
////////////////////////////////////////////////////////////////////////////////
// FILE LOADING
////////////////////////////////////////////////////////////////////////////////
    
    /*
     * Loads the current .map file, from a predefined location.
     */
    private static void loadMapFromFile()
    {
        try {
            
            ArrayList<String> list = new ArrayList<String>();

            Scanner scanner = new Scanner(new File(FILESPATH + mapNum + ".map"));
            scanner.useDelimiter("\n");

            while (scanner.hasNext()) { list.add(scanner.next()); }
            scanner.close();
                
                char[][] mapTemp = new char[list.size()][];
                
                for (int i = 0; i < list.size(); i++) { mapTemp[i] = list.get(i).toCharArray(); }
                
                map = mapTemp.clone(); disMap = mapTemp.clone();
                    
                for (int i = 0; i < mapTemp.length; i++)
                { map[i] = mapTemp[i].clone(); disMap[i] = mapTemp[i].clone(); }
                
        } catch (Exception e) { System.err.println(e.getMessage()); }
    }
    
    /*
     * Loads the current .map file, from a predefined location.
     */
    private static void loadDataFromFile()
    {
        try {
            
            ArrayList<String> list = new ArrayList<String>();

            Scanner scanner = new Scanner(new File(FILESPATH + mapNum + ".dat"));
            scanner.useDelimiter("\n");

            while (scanner.hasNext()) { list.add(scanner.next()); }
            scanner.close();
                
                String[][] mapDataTemp = new String[list.size()][];
                
                for (int i = 0; i < list.size(); i++)
                {
                    mapDataTemp[i] = list.get(i).split(":");
                }

                mapData = mapDataTemp.clone();
                
                for (int i = 0; i < mapDataTemp.length; i++)
                { mapData[i] = mapDataTemp[i].clone(); }
                
                
        } catch (Exception e) { System.err.println(e.getMessage()); }
    }
    
////////////////////////////////////////////////////////////////////////////////
// FILE PARSING
////////////////////////////////////////////////////////////////////////////////
    
    /*
     * Searches the current map and gets the start and exit positions in terms of X and Y
     */
    private static void parseMap()
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
    
    /**
     * Searches the current data and gets the following:
     * - Entities
     *      - NPC
     *          - Position
     *          - Name
     *          - Dialog
     *      - Monster
     *          - Position
     *          - Name
     * - Dialog
     *      - Start
     *      - Exit
     */
    private static void parseData()
    {
        dialogStart = "";
        dialogExit = "";
        
        for (int y = 0; y < mapData.length; y++)
        {
            if (mapData[y][0].equalsIgnoreCase("DialogStart"))
            {
                dialogStart = mapData[y][1];
            } else if (mapData[y][0].equalsIgnoreCase("DialogExit"))
            {
                dialogExit = mapData[y][1];
            } else if (mapData[y][0].equalsIgnoreCase("PosStart"))
            {
                startX = Integer.parseInt(mapData[y][1]);
                startY = Integer.parseInt(mapData[y][2]);
            } else if (mapData[y][0].equalsIgnoreCase("PosExit"))
            {
                exitX = Integer.parseInt(mapData[y][1]);
                exitY = Integer.parseInt(mapData[y][2]);
            }
        }
    }
    
////////////////////////////////////////////////////////////////////////////////
// MAP CONVERSION
////////////////////////////////////////////////////////////////////////////////
    
    /*
     * Replaces '#'s with the appropreate wall icon.
     */
    private static void renderDispMap()
    {
        char w = '#', d = '+';
        
        for(int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                
                byte a = 0;
                
                if (map[y][x]==w) {
                    try { if (map[y][x+1]==w || map[y][x+1]==d){a+=1;} } catch (Exception e) {}   // x++   1
                    try { if (map[y][x-1]==w || map[y][x-1]==d){a+=2;} } catch (Exception e) {}   // x--   2
                    try { if (map[y+1][x]==w || map[y+1][x]==d){a+=4;} } catch (Exception e) {}   // y++   4
                    try { if (map[y-1][x]==w || map[y-1][x]==d){a+=8;} } catch (Exception e) {}   // y--   8
                }
                
                switch(a) {
                    case 15: disMap[y][x] = (char) 197; break;  //all
                        
                    case 14: disMap[y][x] = (char) 180; break;  // x-- y++ y--
                    case 13: disMap[y][x] = (char) 195; break;  // x++ y++ y--
                    case  7: disMap[y][x] = (char) 194; break;  // x++ x-- y++
                    case 11: disMap[y][x] = (char) 193; break;  // x++ x-- y--
                        
                    case  5: disMap[y][x] = (char) 218; break;  // x++ y++
                    case  9: disMap[y][x] = (char) 192; break;  // x++ y--
                    case  6: disMap[y][x] = (char) 191; break;  // x-- y++
                    case 10: disMap[y][x] = (char) 217; break;  // x-- y--
                        
                    case  1:
                    case  2:
                    case  3: disMap[y][x] = (char) 196; break;  // x++ x--
                    case  4:
                    case  8:
                    case 12: disMap[y][x] = (char) 179; break;  // y++ y--
                }
            }
        }
    }
}