package teapot_saga_iv.maps;

import java.util.ArrayList;
import java.util.List;
import teapot_saga_iv.Monster;
import teapot_saga_iv.line_of_sight.LineOfSight;


public class Overworld extends MapData {

    
        
    
    public Overworld(char[][] map, String[][] mapDat) {
        this.mapData = mapDat;
        this.map = map;
        this.disMap = clone(map);

        //createRandomMap(map[0].length, map.length, seed, 20);

        parseData();
        parseMap();
        renderDisMap();

        seen = fill( clone(map), '1' );
    }
    
    @Override
    public void update()
    {
        playerAtStaircase();
    }
    
    @Override
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
                if (mapData[i][0].equalsIgnoreCase("Staircase"))
                {
                    int x = Integer.parseInt( mapData[i][1] );
                    int y = Integer.parseInt( mapData[i][1] );
                    int destWorld = Integer.parseInt( mapData[i][1] );
                    int destLevel = Integer.parseInt( mapData[i][1] );
                    
                    stairs.add( new Staircase(x, y, destWorld, 1) );
                }
            }
        } catch (Exception e) {}
    }
    
    
} 