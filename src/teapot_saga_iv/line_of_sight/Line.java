package teapot_saga_iv.line_of_sight;

import java.util.ArrayList;  
import java.util.List;  

/*
 * Bresenham's line algorithm:
 * 
 * http://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm
 */


public class Line {    
    
    private List<Point> points;
    
    /**
     * generates a line from
     */
    public Line(int startX, int startY, int endX, int endY) {
        
        points = new ArrayList<Point>();
        
        int difX = Math.abs(endX-startX);
        int difY = Math.abs(endY-startY);
        
        int incrementX = startX < endX ? 1 : -1;
        int incrementY = startY < endY ? 1 : -1;
        
        int err = difX-difY;
        
        while (true)
        {
            points.add(new Point(startX, startY));
            
            if (startX==endX && startY==endY)
            {
                break;
            }
            
            int e2 = err * 2;
            
            if (e2 > -difX)
            {
                err -= difY;
                startX += incrementX;
            }
            
            if (e2 < difX)
            {
                err += difX;
                startY += incrementY;
            }
        }    
    }
    
    /**
     * returns a list containing all the points in the line created by the constructer.
     */
    public List<Point> getPoints()
    { 
        return points;
    }
}