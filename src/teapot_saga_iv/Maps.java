package teapot_saga_iv;


import java.io.File;
import java.util.Scanner;



public class Maps {
    
    public static String map;

    public Maps()
    {
        LoadCurrentMap();
    }
    
    public static void LoadCurrentMap() 
    {
        map = null;
        
        try {
        String file = System.getProperty("user.dir") + "/src/teapot_saga_iv/maps/" + getMapNumber() + ".txt";
        String map = new Scanner(new File(file)).useDelimiter("\\Z").next();
        } catch (Exception e) {}
        
        System.out.println(map);
        Window.setText(map);
    }
    
    public static int getMapNumber()
    {
        return Main.lvl;
    }
} 