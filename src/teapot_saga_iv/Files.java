package teapot_saga_iv;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Files {

    public static List<MapData> data = new ArrayList<MapData>();
    private static MapData overworld;
    
    private static MapData currentMapData;
    
    public static final String FILESPATH = System.getProperty("user.dir") + "/src/teapot_saga_iv/files/";
    
    public static int world = 1, level = 0;
    

    
    
    public static char[][] currentMap()
    { return currentMapData().getMap(); }
    
    public static char[][] currentDisMap()
    { return currentMapData().getDisMap(); }
    
    public static MapData currentMapData()
    {
        if (Main.isAtOverworld)
        {
            return overworld;
        }
        else
        {
            if (currentMapData != null)
            {
                if (currentMapData.getWorld() == world && currentMapData.getLevel() == level)
                {
                    return currentMapData;
                }

            } else {

                for (MapData dat : data)
                {
                    if (dat.getWorld() == world && dat.getLevel() == level)
                    {
                        return dat;
                    }
                }
            }
        }
        
        return null;
    }
    
    
    
    
    public static void loadAllMapData()
    {
        overworld = new Overworld( loadMap("overworld"), loadMapData("overworld") );
        loadMaps();
    }

    

    /**
     * Loads the current .dat and .map file, from a predefined location.
     */
    private static void loadMaps()
    {
        data.clear();
        
        int world = 1, lvl = 1;
        
        do
        {
            if (!mapExists(world, lvl)) {
                world++;
                lvl = 1;
                
                if (!mapExists(world, lvl)) { break; }
            }
            System.out.println(world + " " + lvl);
            
            
            char[][] map = loadMap(world + "." + lvl);
            String[][] mapData = loadMapData(world + "." + lvl);
            
            
            data.add( new Map(world, lvl, map, mapData ) );
            
            printCharMap(map);
            
            lvl++;
        } while (true);
    }
    
    
    
    
    
    
    
    
    public static void printCharMap(char[][] map)
    {
        for (char[] a : map)
            {
                for (char b : a)
                {
                    System.out.print(b);
                }
                System.out.println();
            }
    }
    

    /**
     * Checks weather the specified map exists.
     */
    public static boolean mapExists(int world, int level)
    {
        File file = new File(FILESPATH + world + "." + level + ".map");
        //System.out.println(file.exists());
        return file.exists();
    }

    
    
    
    
    
    /**
     * Loads the current .map file, from a predefined location.
     */
    private static char[][] loadMap(String string)
    {
        char[][] mapTemp = null;
        
        try {
            
            ArrayList<String> list = new ArrayList<String>();

            Scanner scanner = new Scanner(new File(FILESPATH + string + ".map"));
            
            scanner.useDelimiter("\n");

            while (scanner.hasNext()) { list.add(scanner.next()); }
            scanner.close();
                
                mapTemp = new char[list.size()][];
                
                for (int i = 0; i < list.size(); i++) {
                    mapTemp[i] = list.get(i).replace("\r", "").toCharArray();
                }
                
        } catch (Exception e) { System.err.println(e.getMessage()); }
        
        return mapTemp;
    }
    
    
    
    /**
     * Loads the current .map file, from a predefined location.
     */
    private static String[][] loadMapData(String string)
    {
        String[][] mapDataTemp = null;
        
        try {
            
            ArrayList<String> list = new ArrayList<String>();

            Scanner scanner = new Scanner(new File(FILESPATH + string + ".dat"));
            scanner.useDelimiter("\n");

            while (scanner.hasNext()) { list.add(scanner.next()); }
            scanner.close();
                
                mapDataTemp = new String[list.size()][];
                
                for (int i = 0; i < list.size(); i++)
                {
                    mapDataTemp[i] = list.get(i).split(":");
                }
                return mapDataTemp;
        } catch (Exception e) { System.err.println(e.getMessage()); }
        
        return mapDataTemp;
    }
}