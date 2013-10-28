package teapot_saga_iv;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import teapot_saga_iv.line_of_sight.LineOfSight;


public class MapData {
    
    int world, level;
    
    char map[][], disMap[][];
    
    String[][] mapData;
    
    int startX = 1, startY = 1, exitX = 2, exitY = 2;
    
    
    
    String dialogStart = "";
    String defaultDialog = "";
    
    LineOfSight sight;
    
    private DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH");
    private Date date = new Date();
    private int seed = Integer.parseInt(dateFormat.format(date));
    Random random = new Random(seed);
    
    
    List<Staircase> stairs = new ArrayList<Staircase>();
    List<Entity> entities = new ArrayList<Entity>();
    List<Entity> removeEntities = new ArrayList<Entity>();
    
    /**
     * returns the current map.
     * @return 
     */
    public char[][] getMap()
    { return map; }
    
    /**
     * returns the current Display Map
     * @return 
     */
    public char[][] getDisMap()
    { return disMap; }
    
    /**
     * returns a 2D char array with all the seen tiles of the map.
     * @return 
     */
    public char[][] getSeen()
    { 
        return sight.getVisible();
    }
    
    
    /**
     * returns a list with all the entities of the map.
     * @return 
     */
    public List<Entity> getEntities()
    { return entities; }
    
    /**
     * Checks if an entity with the specified name is on the map.
     * Returns true if it exists.
     * @param e 
     */
    public boolean isEntity(String a)
    {
        for (Entity e : entities)
        {
            if (e.getType().equalsIgnoreCase(a))
            {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Adds an entity to the map.
     * @param e 
     */
    public void addEntity(Entity e)
    { entities.add(e); }
    
    
    /**
     * returns a char from the Map based on the X & Y coords passed.
     * @param x
     * @param y
     * @return 
     */
    public char getMapTile(int x, int y)
    { return map[y][x]; }
    
    /**
     * returns a char from the Display Map based on the X & Y coords passed.
     * @param x
     * @param y
     * @return 
     */
    public char getDisMapTile(int x, int y)
    { return disMap[y][x]; }
    
    
    /**
     * Sets the specified x & y coord on the Map to the specified char.
     * @param x
     * @param y
     * @param a 
     */
    public void setMapTile(int x, int y, char a)
    { map[y][x] = a; }
    
    /**
     * Sets the specified x & y coord on the Display Map to the specified char.
     * @param x
     * @param y
     * @param a 
     */
    public void setDisMapTile(int x, int y, char a)
    { map[y][x] = a; }
    
    
    /**
     * Checks if the specified position is a start staircase.
     * @param x
     * @param y
     * @return 
     */
    public boolean isStart(int x, int y)
    { return map[y][x] == '<'; }
    
    /**
     * Checks if the specified position is an exit staircase.
     * @param x
     * @param y
     * @return 
     */
    public boolean isExit(int x, int y)
    { return map[y][x] == '>'; }
    
    
    /**
     * Returns the X coord of the players start position for the map.
     * @return 
     */
    public int getStartX()
    { return startX; }
    /**
     * Returns the Y coord of the players start position for the map.
     * @return 
     */
    public int getStartY()
    { return startY; }
    
    /**
     * Returns the X coord of the players exit position for the map.
     * @return 
     */
    public int getExitX()
    { return exitX; }
    /**
     * Returns the Y coord of the players exit position for the map.
     * @return 
     */
    public int getExitY()
    { return exitY; }
    
    
    /**
     * Returns the maps level number
     * @return 
     */
    public int getLevel()
    { return level; }
    
    /**
     * Returns the maps world number
     * @return 
     */
    public int getWorld()
    { return world; }
    
    /**
     * Returns a list containing all the stairs of the map.
     * @return 
     */
    public List<Staircase> getStairs()
    { return stairs; }
    
    
    /**
     * Returns the maps starting dialog.
     * @return - The maps starting dialog
     */
    public String getDialogStart()
    { return dialogStart; }
    
    /**
     * Returns the maps default dialog.
     * @return - The default dialog 
     */
    public String getDefaultDialog()
    { return defaultDialog; }
    
    /**
     * Sets the maps default dialog.
     * @param a - The default dialog.
     */
    public void setDefaultDialog(String a)
    { defaultDialog = a; }
    
    
    /**
     * Re-renders and parses the current map to use any changes made to it.
     */
    public void rerender()
    {
        parseMap();
        renderDisMap();
    }
    
    
    /**
     * Queues an entity for removal to avoid errors.
     */
    public void queueEntityForRemoval(Entity e)
    {
        removeEntities.add(e);
    }
    
    /**
     * Removes all queued entities placed in the "removeEntities" list from the main list containing all the entities.
     */
    public void removeEntities()
    {
        for (Entity e : removeEntities)
        {
            entities.remove(e);
        }
        
        removeEntities.clear();
    }
    
    /**
     * An overridable method required by all the children classes, this is run after every game tick.
     */
    public void update() {}
    
    
    public void canUpdate(boolean b) {}
    
    /**
     *  Clones a 2D char array and returns it.
     * 
     * @param a - The array to be cloned
     * @return  - The duplicate array
     */
    char[][] clone(char[][] a)
    {
        char[][] b = a.clone();

        for (int i = 0; i < a.length; i++)
        {
            b[i] = a[i].clone();
        }
        
        return b;
    }

   /**
    * Searches the current data and for the Start, Exit, Start Dialog and any NPC's, it then creates them..
    */
    void parseData()
    {
        try
        {
            dialogStart = "";
            entities.clear();
            entities = new ArrayList<Entity>();

            for (int i = 0; i < mapData.length; i++)
            {
                if (mapData[i][0].equalsIgnoreCase("PosStart"))
                {
                    startX = Integer.parseInt(mapData[i][1]);
                    startY = Integer.parseInt(mapData[i][2]);
                }
                
                if (mapData[i][0].equalsIgnoreCase("PosExit"))
                {
                    exitX = Integer.parseInt(mapData[i][1]);
                    exitY = Integer.parseInt(mapData[i][2]);
                }
                
                
                
                if (mapData[i][0].equalsIgnoreCase("DialogStart"))
                {
                    dialogStart = mapData[i][1];
                }
                
                if (mapData[i][0].equalsIgnoreCase("Entity"))
                {
                    int x = Integer.parseInt(mapData[i][1]);
                    int y = Integer.parseInt(mapData[i][2]);
                    String type = mapData[i][3].toLowerCase();

                    entities.add( createNewEntity(x, y, type) );
                }
                
                if (mapData[i][0].equalsIgnoreCase("Staircase"))
                {
                    int x = Integer.parseInt( mapData[i][1] );
                    int y = Integer.parseInt( mapData[i][2] );
                    int destWorld = Integer.parseInt( mapData[i][3] );
                    int destLevel = Integer.parseInt( mapData[i][4] );
                    char symbol =  mapData[i][5].charAt(0);
                    
                    stairs.add( new Staircase(x, y, destWorld, destLevel, symbol) );
                }
            }
        } catch (Exception e) {}
    }
    
    /**
     * returns a new entity based on the values passed.
     * 
     * @param x - The X position.
     * @param y - The Y position.
     * @param type - The name of the entity that will be created.
     * @return - The entity that was created.
     */
    private Entity createNewEntity(int x, int y, String type)
    {
        
        if      (type.equalsIgnoreCase("prisoner"))     { return new Monster_Prisoner       (x, y); }
        else if (type.equalsIgnoreCase("golem"))        { return new Monster_Golem          (x, y); }
        else if (type.equalsIgnoreCase("teageist"))     { return new Monster_Teageist       (x, y); }
        else if (type.equalsIgnoreCase("teageistboss")) { return new Monster_TeageistBoss   (x, y); }
        else if (type.equalsIgnoreCase("teagiestminion"))   { return new Monster_TeageistMinion (x, y); }
        else if (type.equalsIgnoreCase("healthpotion")) { return new Item_HealthPotion      (x, y); }
        else if (type.equalsIgnoreCase("soldier"))      { return new Monster_Soldier        (x, y); }
        else                                            { return new Monster_Bat            (x, y); }
    }
    
   /**
    * Searches the current map and gets the start and exit positions in terms of X and Y.
    * Also makes sure that open doors are the correct case (they should be upper case).
    */
    void parseMap()
    {
//        int wallCount = 0;
        
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == '<') { //creates a new staircase going to the previous level
                    startX = x;
                    startY = y;
                    stairs.add( new Staircase(x, y, world, level - 1, '<') );
                } else if (map[y][x] == '>') { //creates a new staircase going to the next level
                    exitX = x;
                    exitY = y;
                    stairs.add( new Staircase(x, y, world, level + 1, '>') );
                }  else if (map[y][x] == 'x') { //makes sure that lowercase X's are converted to open doors which should be upper case.
                    map[y][x] = 'X';
                }
            }
        }
        
//        System.out.println(world + " " + level + " walls: " + wallCount);
    }
    
   /**
    * Replaces walls and characters with the appropriate character needed for rendering
    */
    void renderDisMap()
    {
 
        @SuppressWarnings("unused")
		char wall = '#', door = '+', space = ' ', floor = '.', tea='t';

        int ran = 0;
        
        for(int y = 0; y < map.length; y++)
        {
            for (int x = 0; x < map[y].length; x++)
            {
                
                byte a = 0;
                
                if (map[y][x]==wall) {
                    disMap[y][x] = (char) 219;
                    
                    try { if (map[y][x+1]==wall || map[y][x+1]==door){a+=1;} } catch (Exception e) {} // x++ 1
                    try { if (map[y][x-1]==wall || map[y][x-1]==door){a+=2;} } catch (Exception e) {} // x-- 2
                    try { if (map[y+1][x]==wall || map[y+1][x]==door){a+=4;} } catch (Exception e) {} // y++ 4
                    try { if (map[y-1][x]==wall || map[y-1][x]==door){a+=8;} } catch (Exception e) {} // y-- 8
                    
                    switch(a) {
                        case 15: disMap[y][x] = (char) 197; break; //all
                        
                        case 14: disMap[y][x] = (char) 180; break; // x-- y++ y--
                        case 13: disMap[y][x] = (char) 195; break; // x++ y++ y--
                        case 7: disMap[y][x] = (char) 194; break; // x++ x-- y++
                        case 11: disMap[y][x] = (char) 193; break; // x++ x-- y--
                        
                        case 5: disMap[y][x] = (char) 218; break; // x++ y++
                        case 9: disMap[y][x] = (char) 192; break; // x++ y--
                        case 6: disMap[y][x] = (char) 191; break; // x-- y++
                        case 10: disMap[y][x] = (char) 217; break; // x-- y--
                        
                        case 1:
                        case 2:
                        case 3: disMap[y][x] = (char) 196; break; // x++ x--
                        case 4:
                        case 8:
                        case 12: disMap[y][x] = (char) 179; break; // y++ y--
                    }
                }
                else if (map[y][x]==floor) {
                    disMap[y][x] = (char) 7;
                }
                else if (map[y][x]==tea) {
                    ran = (int)(random.nextDouble()*3);
                    
                    if (ran==0) {
                        disMap[y][x] = (char) 7;
                    } else if (ran==1) {
                        disMap[y][x] = (char) 177;
                    } else {
                        disMap[y][x] = (char) 178;
                    }
                }
            }
        }
    }
    
    /**
     * Fills the specified 2D char array with the specified character and returns the output.
     * @param array
     * @param a
     * @return 
     */
    char[][] fill(char[][] array, char a)
    {
        for(char[] y : array)
        {
            for (@SuppressWarnings("unused") char x : y)
            {
                x = a;
            }
        }
        
        return array;
    }
} 