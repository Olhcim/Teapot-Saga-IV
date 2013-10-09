package teapot_saga_iv.maps;

import teapot_saga_iv.Main;
import teapot_saga_iv.Monster;
import teapot_saga_iv.line_of_sight.LineOfSight;


public class Map extends MapData {
    
    public Map(int world, int level, char[][] map, String[][] mapDat)
    {
        this.world = world;
        this.level = level;
        this.mapData = mapDat;
        this.map = map;
        this.disMap = clone(map);

        //createRandomMap(map[0].length, map.length, seed, 20);

        parseData();
        parseMap();
        renderDisMap();

        sight = new LineOfSight(this);
        seen = fill( clone(map), '1' );
    }
    
    

    
    
    @Override
    public void update()
    {
        playerAtStaircase();
        updateSeen();
        updateMonsters();
        
    }
    
    
    
    
        
    public void updateSeen()
    {
        sight.update(Main.p.getX(), Main.p.getY());
        
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
    
    public void updateMonsters()
    {
        for (Monster m : monsters)
        {
            m.moveTowardsPlayer();
        }
    }
    
    
} 