package teapot_saga_iv.maps;

import java.util.Collections;
import teapot_saga_iv.Main;
import teapot_saga_iv.characters.Monster;
import teapot_saga_iv.line_of_sight.LineOfSight;



public class Map extends MapData {
    
    public Map(int world, int level, char[][] map, String[][] mapDat)
    {
        this.world = world;
        this.level = level;
        this.mapData = mapDat;
        this.map = map;
        this.disMap = clone(map);

        
        parseMap();
        renderDisMap();
        parseData();
        
        
        sight = new LineOfSight(this);
    }
    
    

    
    
    @Override
    public void update()
    {
        updateSeen();
        updateMonsters();
        removeMonsters();
        
    }
    
    
    
    
        
    public void updateSeen()
    {
        sight.update(Main.getPlayer().getX(), Main.getPlayer().getY());
    }
    
    public void updateMonsters()
    {
        for (Monster m : monsters)
        {
            m.update();
        }
        Collections.shuffle(monsters);
    }
    
    
} 