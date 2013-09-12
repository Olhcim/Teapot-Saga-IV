package teapot_saga_iv;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;



public class Maps {
    
    public static char map[][];
    public static String mapData[][];
    public static int mapNum = 1;
    public static int startX, startY, exitX, exitY;
    
    
    public static void loadCurrentMap()
    {
        try {
            
            ArrayList<String> list = new ArrayList<String>();
            
            File file = new File(System.getProperty("user.dir")
                    + "/src/teapot_saga_iv/maps/"
                    + mapNum + ".map");

            Scanner scanner = new Scanner(file);
            
            scanner.useDelimiter("\n");

            while (scanner.hasNext())
            {
                list.add(scanner.next());
            }
            scanner.close();

            //convert the arraylist to a char array
            
            char[][] mapTemp = new char[list.size()][];
            
            for (int i = 0; i < list.size(); i++)
            {
                mapTemp[i] = list.get(i).toCharArray();
            }

//------------------------------------------------------------------------------
            for (int i = 0; i < mapTemp.length; i++)
            {
                for (int j = 0; j < mapTemp[i].length; j++)
                {
                    System.out.print(mapTemp[i][j]);
                }
                System.out.println();
            }
//------------------------------------------------------------------------------
            
            map = mapTemp;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    public static void loadCurrentMapData()
    {
        try {
            
            ArrayList<String> list = new ArrayList<String>();
            
            File file = new File(System.getProperty("user.dir")
                    + "/src/teapot_saga_iv/maps/"
                    + mapNum + ".data");

            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\n");

            while (scanner.hasNext())
            {
                list.add(scanner.next());
            }
            
            scanner.close();

            //convert the arraylist to a String array
            
            String[][] mapDataTemp = new String[list.size()][];
            
            for (int i = 0; i < list.size(); i++)
            {
                mapDataTemp[i] = list.get(i).toLowerCase().split(",");
            }

            mapData = mapDataTemp;


        } catch (Exception e) {
            e.printStackTrace();
        }
        
        parseCurrentMapData();

    }
    
    public static void parseCurrentMapData()
    {
            for (int i = 0; i < mapData.length; i++)
            {
                if(mapData[i][0].toLowerCase().contains("start"))
                {
                    startX = Integer.parseInt(mapData[i][1]);
                    startY = Integer.parseInt(mapData[i][2]);
                    
                    Player.x = startX;
                    Player.y = startY;
                    
                    System.out.println("PlayerPos: " + mapData[i][1] + " " + mapData[i][2]);
                    
                } else if (mapData[i][0].toLowerCase().contains("exit"))
                {
                    exitX = Integer.parseInt(mapData[i][1]);
                    exitY = Integer.parseInt(mapData[i][2]);
                   System.out.println("MapExit: " +Integer.parseInt(mapData[i][1]) + " " + Integer.parseInt(mapData[i][2]));
                }
            }
    }

} 