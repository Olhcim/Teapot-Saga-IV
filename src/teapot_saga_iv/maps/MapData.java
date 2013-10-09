package teapot_saga_iv.maps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import teapot_saga_iv.*;
import teapot_saga_iv.line_of_sight.LineOfSight;


public class MapData {
    
    int world, level;
    
    char map[][], disMap[][];
    
    String[][] mapData;
    
    int startX = 1, startY = 1, exitX = 2, exitY = 2;
    
    
    
    String dialogStart;
    String dialogExit;
    
    LineOfSight sight;
    char[][] seen;
    
    
    private DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH");
    private Date date = new Date();
    private int seed = Integer.parseInt(dateFormat.format(date));
    Random random = new Random(seed);
    
    
    List<Staircase> stairs = new ArrayList<Staircase>();
    List<Monster> monsters = new ArrayList<Monster>();
    
    
    
    
    public char[][] getMap()
    { return map; }
    
    public char[][] getDisMap()
    { return disMap; }
    
    public char[][] getSeen()
    { 
        for (char[] y : seen)
        {
            for (char x : y)
            {
                System.out.print(x);
            }
            System.out.println();
        }
        return seen;
    }
    
    
    
    public List<Monster> getMonsters()
    { return monsters; }
    
    
    
    public char getMapTile(int x, int y)
    { return map[y][x]; }
    
    public char getDisMapTile(int x, int y)
    { return disMap[y][x]; }
    
    
    
    public void setMapTile(int x, int y, char a)
    { map[y][x] = a; }
    
    public void setDisMapTile(int x, int y, char a)
    { map[y][x] = a; }
    
    
    
    public boolean isStart(int x, int y)
    { return map[y][x] == '<'; }
    
    public boolean isExit(int x, int y)
    { return map[y][x] == '>'; }
    
    
    
    public int getStartX()
    { return startX; }
    public int getStartY()
    { return startY; }
    
    public int getExitX()
    { return exitX; }
    public int getExitY()
    { return exitY; }
    
    
    
    public int getLevel()
    { return level; }
    
    public int getWorld()
    { return world; }
    
    
    
    public String getDialogStart()
    { return dialogStart; }
    
    public String getDialogExit()
    { return dialogExit; }
    
    
    
    
    public void update() {};
    
    
    
    
    public void playerAtStaircase()
    {
        for (Staircase s : stairs)
        {
            s.checkForPlayer();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
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
    * Searches the current data and for the Start, Exit, Start Dialog and any NPC's.
    */
    void parseData()
    {
        try
        {
            dialogStart = "";
            dialogExit = "";
            monsters.clear();
            monsters = new ArrayList<Monster>();

            for (int i = 0; i < mapData.length; i++)
            {
                if (mapData[i][0].equalsIgnoreCase("DialogStart"))
                {
                    dialogStart = mapData[i][1];
                }
                
                if (mapData[i][0].equalsIgnoreCase("DialogExit"))
                {
                    dialogExit = mapData[i][1];
                }
                
                if (mapData[i][0].equalsIgnoreCase("Monster"))
                {
                    int x = Integer.parseInt(mapData[i][1]);
                    int y = Integer.parseInt(mapData[i][2]);
                    String type = mapData[i][3].toLowerCase();

                    monsters.add(new Monster(x, y, type));
                }
                
                if (mapData[i][0].equalsIgnoreCase("Staircase"))
                {
                    int x = Integer.parseInt( mapData[i][1] );
                    int y = Integer.parseInt( mapData[i][1] );
                    int destWorld = Integer.parseInt( mapData[i][1] );
                    int destLevel = Integer.parseInt( mapData[i][1] );
                    
                    stairs.add( new Staircase(x, y, destWorld, destLevel) );
                }
            }
        } catch (Exception e) {}
    }
    
   /**
    * Searches the current map and gets the start and exit positions in terms of X and Y.
    */
    void parseMap()
    {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == '<') {
                    //stairs.add(new Staircase(x, y, world, level++));
                } else if (map[y][x] == '>') {
                    //stairs.add(new Staircase(x, y, world, level--));
                }
            }
        }
    }
    
   /**
    * Replaces walls and characters with the appropriate character needed for rendering
    */
    void renderDisMap()
    {
 
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
                        disMap[y][x] = (char) 176;
                    } else if (ran==1) {
                        disMap[y][x] = (char) 177;
                    } else {
                        disMap[y][x] = (char) 178;
                    }
                }
                
                
                for (Staircase s : stairs)
                {
                    if (s.x == x && s.y == y)
                    {
                        disMap[y][x] = s.getSymbol();
                        map[y][x] = s.getSymbol();
                    }
                }
            }
        }
    }
    
    char[][] fill(char[][] array, char a)
    {
        for(char[] y : array)
        {
            for (char x : y)
            {
                x = a;
            }
        }
        
        return array;
    }
} 