package teapot_saga_iv;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import teapot_saga_iv.line_of_sight.LineOfSight;


public class MapData {
    
    private int lvl;
    private int world;
    
    private char map[][];
    private char disMap[][];
    
    private String[][] mapData;
    
    private int startX = 1, startY = 1, exitX = 2, exitY = 2;
    
    String dialogStart;
    String dialogExit;
    
    private LineOfSight sight;
    private char[][] seen;
    
    
    List<Monster> monsters = new ArrayList<Monster>();
    
    private static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH");
    private static Date date = new Date();
    private static int seed = Integer.parseInt(dateFormat.format(date));    // seed used for 'random' maps - seed changes every hour.
    

    public MapData(int world, int lvl, char[][] map, String[][] mapDat)
    {
        this.world = world;
        this.lvl = lvl;
        
        this.mapData = mapDat;
        this.map = map;
        this.disMap = clone(map);
        
        parseMap();
        renderDisMap();
        
        try { parseData(); } catch (Exception e) {e.getMessage();}
        
        sight = new LineOfSight(this);
        
        seen = clone(map);
        
        for(int y=0; y<map.length; y++) { for(int x=0; x<map[y].length; x++) { seen[y][x] = '0'; }}
    }
    
    
    
    
    public char[][] getMap()
    { return map; }
    
    public char[][] getDisMap()
    { return disMap; }
    
    public char[][] getSeen()
    { return seen; }
    
    
    public void updateSeen()
    {
        sight.update(Main.p.x, Main.p.y); //verbose option becomes increasingly resource intensive with large maps.
        
        for (int y = 0; y < sight.getVisible().length; y++)
        {
            for (int x = 0; x < sight.getVisible()[0].length; x++)
            {
                if(seen[y][x] == '0' && sight.getVisible()[y][x] == '1')
                {
                    seen[y][x] = '1';
                }
            }
        }
    }
    
    
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
    
    
    public int getLvl()
    { return lvl; }
    
    public int getWorld()
    { return world; }
    
    
    public List getMonsters()
    { return monsters; }
    
    
    
    
    
    
    
    public void updateMonsters()
    {
        for (Monster m : monsters)
        {
            m.moveTowardsPlayer();
        }
    }
    
    
    
    
    
    
    
    
    private char[][] clone(char[][] a)
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
    private void parseData()
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
            } else if (mapData[i][0].equalsIgnoreCase("Monster"))
            {
                int x = Integer.parseInt(mapData[i][1]);
                int y = Integer.parseInt(mapData[i][2]);
                String type = mapData[i][3].toLowerCase();
                
                monsters.add(new Monster(x, y, type));
            }
        }
    }
    
    /**
* Searches the current map and gets the start and exit positions in terms of X and Y.
*/
    private void parseMap()
    {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (isStart(x,y)) {
                    startX = x;
                    startY = y;
                } else if (isExit(x,y)) {
                    exitX = x;
                    exitY = y;
                }
            }
        }
    }
    
    /**
* Replaces walls and characters with the appropriate character needed for rendering
*/
    private void renderDisMap()
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
                    ran = (int)(Math.random()*3);
                    
                    if (ran==0) {
                        disMap[y][x] = (char) 176;
                    } else if (ran==1) {
                        disMap[y][x] = (char) 177;
                    } else {
                        disMap[y][x] = (char) 178;
                    }
                }
            }
        }
    }
    
    
    
    
    
    
    
    
    
    public void createRandomMap(int breadth, int length, int seed, int noise)
    {
        monsters.clear();
        
        Random gen = new Random(seed);
        int ran = 0;
        
        char[][] mapTemp = new char[length][breadth];
        
        for(int y = 0; y < mapTemp.length; y++)
        {
            for(int x = 0; x < mapTemp[y].length; x++)
            {   
                
                if(x==2 && y==2)
                {
                    mapTemp[y][x] = '<';
                }
                else if(y==mapTemp.length-3 && x==mapTemp[y].length-3)
                {
                    mapTemp[y][x] = '>';
                }
                else if(y==0 || y==mapTemp.length-1 || x==0 || x==mapTemp[y].length-1)
                {
                    mapTemp[y][x] = '#';
                } else {
                    
                    ran = (int)(gen.nextDouble()*noise);

                    if(ran==0)
                    {
                        mapTemp[y][x] = '#';
                    }
                    else
                    {
                        mapTemp[y][x] = '.';
                    }
                }
                
                ran = (int) (gen.nextDouble()*(mapTemp.length * mapTemp[y].length));
                
                if (ran < 3 && mapTemp[y][x] == '.')
                {
                    monsters.add(new Monster(x,y,Monster.DEFAULT));
                }
                
                
            }
        }
        

        map = clone(mapTemp);
    }
} 