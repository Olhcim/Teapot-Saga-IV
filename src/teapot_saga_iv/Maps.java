package teapot_saga_iv;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;



public class Maps {
    
    public static char map[][];
    public static String mapData[];
    public static int mapNum = 2;

    
    
    public Maps()
    {
        loadCurrentMap();
    }
    
    
    
//    public static void loadCurrentMap() 
//    {
//        
//        String a = "";
//        try {
//            File file = new File(System.getProperty("user.dir")
//                    + "/src/teapot_saga_iv/maps/"
//                    + mapNum + ".txt");
//            
//            Scanner scan = new Scanner(file);
//            a = scan.useDelimiter("\\Z").next();
//            
//        } catch (Exception e) { System.err.println(e.getMessage()); }
//        
//        map = a.split("\r");
//        System.out.println(map.toString().length() + " > " + map[4].toCharArray().length);
//    }
    
    public static void loadCurrentMap()
    {
        try {
            
            ArrayList<String> list = new ArrayList<String>();
            
            File file = new File(System.getProperty("user.dir")
                    + "/src/teapot_saga_iv/maps/"
                    + mapNum + ".txt");

            Scanner scanner = new Scanner(file);
            
            scanner.useDelimiter(System.getProperty("line.separator"));

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

            for (int i = 0; i < mapTemp.length; i++)
            {
                for (int j = 0; j < mapTemp[i].length; j++)
                {
                    System.out.print(mapTemp[i][j]);
                }
                System.out.println();
            }
            
            map = mapTemp;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
//    public static void getMapData()
//    {
//        String a = "";
//                try {
//            File file = new File(System.getProperty("user.dir")
//                    + "/src/teapot_saga_iv/maps/"
//                    + mapNum + "data.txt");
//            
//            Scanner scan = new Scanner(file);
//            a = scan.useDelimiter("\\Z").next();
//            
//            
//        } catch (Exception e) { System.err.println(e.getMessage()); }
//                
//        mapData = a.split(",");
//
//    }

} 