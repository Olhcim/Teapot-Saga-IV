package teapot_saga_iv;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;



public class Files {
    
    
    public static char map[][];
    public static char disMap[][];

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
                
                
                map = mapTemp.clone();
                disMap = mapTemp.clone();
                    
                for (int i = 0; i < mapTemp.length; i++)
                {
                    map[i] = mapTemp[i].clone();
                    disMap[i] = mapTemp[i].clone();
                }
                
                
                for (int i = 0; i < map.length; i++) { for (int j = 0; j < map[i].length; j++) { System.out.print(map[i][j]); } System.out.println(); }
                
                parseMap();
                renderDispMap();
                
            } //else {    //loads the data from <mapID>.dat files
//                
//                String[][] mapDataTemp = new String[list.size()][];
//                
//                for (int i = 0; i < list.size(); i++)
//                {
//                    mapDataTemp[i] = list.get(i).toLowerCase().split(",");
//                }
//                
//                mapData = mapDataTemp;
//            }
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    /*
     * Replaces '#'s with the appropreate wall icon.
     */
    public static void renderDispMap()
    {
            
        char w = '#';
        char xp;
        char xn;
        char yp;
        char yn;
        
        for(int y = 0; y < map.length; y++)
        {
            for (int x = 0; x < map[0].length; x++)
            {
                byte a = 0;
                
                if (map[y][x]==w)
                {
                    try { if (map[y][x+1]==w){a+=1;} } catch (Exception e) {}   // x++   1
                    try { if (map[y][x-1]==w){a+=2;} } catch (Exception e) {}   // x--   2
                    try { if (map[y+1][x]==w){a+=4;} } catch (Exception e) {}   // y++   4
                    try { if (map[y-1][x]==w){a+=8;} } catch (Exception e) {}   // y--   8
                }
                
                System.out.print("(" + a + ")");
                
                switch(a)
                {
                    case 15:
                        disMap[y][x] = (char) 197;  //all
                        break;
                        
                    case 14:
                        disMap[y][x] = (char) 180;  // x-- y++ y--
                        break;
                    case 13:
                        disMap[y][x] = (char) 195;  // x++ y++ y--
                        break;
                    case 7:
                        disMap[y][x] = (char) 194;  // x++ x-- y++
                        break;
                    case 11:
                        disMap[y][x] = (char) 193;  // x++ x-- y--
                        break;
                        
                    case 5:
                        disMap[y][x] = (char) 218;  // x++ y++
                        break;
                    case 9:
                        disMap[y][x] = (char) 192;  // x++ y--
                        break;
                    case 6:
                        disMap[y][x] = (char) 191;  // x-- y++
                        break;
                    case 10:
                        disMap[y][x] = (char) 217;  // x-- y--
                        break;
                        
                    case 1:
                    case 2:
                    case 3:
                        disMap[y][x] = (char) 196;  // x++ x--
                        break;
                    case 4:
                    case 8:
                    case 12:
                        disMap[y][x] = (char) 179;  // y++ y--
                        break;
                }
                
            }
            System.out.println();
        }
        
        for (int i = 0; i < disMap.length; i++) { for (int j = 0; j < disMap[i].length; j++) { System.out.print(disMap[i][j]); } System.out.println(); }
        for (int i = 0; i < map.length; i++) { for (int j = 0; j < map[i].length; j++) { System.out.print(map[i][j]); } System.out.println(); }
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