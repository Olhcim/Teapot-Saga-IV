package teapot_saga_iv.maps;

import java.util.Collections;
import teapot_saga_iv.Main;
import teapot_saga_iv.characters.Entity;
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
        sight.fill(('1'));
    }
    
    

    
    
    @Override
    public void update()
    {
        updateSeen();
        updateEntities();
        removeEntities();
        
    }
    
        
    public void updateSeen()
    {
        sight.update(Main.getPlayer().getX(), Main.getPlayer().getY());
    }
    
    public void updateEntities()
    {
        for (Entity e : entities)
        {
            e.update();
        }
        Collections.shuffle(entities);
    }
    
    
} 