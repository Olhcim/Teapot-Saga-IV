package teapot_saga_iv.maps;

import teapot_saga_iv.line_of_sight.LineOfSight;

public class Overworld extends MapData {
    
    public Overworld(char[][] map, String[][] mapDat) {
        this.mapData = mapDat;
        this.map = map;
        this.disMap = clone(map);

        parseData();
        parseMap();
        renderDisMap();

        sight = new LineOfSight(this);
        fill( sight.getVisible(), '1' );
    }
    
    @Override
    public void update() {}
} 