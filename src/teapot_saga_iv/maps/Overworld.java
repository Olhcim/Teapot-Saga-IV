package teapot_saga_iv.maps;

import java.util.ArrayList;
import teapot_saga_iv.characters.Monster;
import teapot_saga_iv.line_of_sight.LineOfSight;


public class Overworld extends MapData {

    private boolean canUpdate = false;
	
    
    public Overworld(char[][] map, String[][] mapDat) {
        this.mapData = mapDat;
        this.map = map;
        this.disMap = clone(map);

        //createRandomMap(map[0].length, map.length, seed, 20);

        parseData();
        parseMap();
        renderDisMap();

        sight = new LineOfSight(this);
        fill( sight.getVisible(), '1' );
    }
    
    @Override
    public void update()
    {
        
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
                    int y = Integer.parseInt( mapData[i][2] );
                    int destWorld = Integer.parseInt( mapData[i][3] );
                    int destLevel = Integer.parseInt( mapData[i][4] );
                    char symbol =  mapData[i][5].charAt(0);
                    
                    stairs.add( new Staircase(x, y, destWorld, destLevel, symbol) );
                }
            }
        } catch (Exception e) {}
    }
    
    
} 