package teapot_saga_iv.line_of_sight;

import java.util.List;
import teapot_saga_iv.Files;
import teapot_saga_iv.MapData;

public class LineOfSight
{
    private char map[][];
    
    private char[][] sight;
    
    public LineOfSight(MapData data)
    {
       sight = data.getMap().clone();
                
        for (int y = 0; y < data.getMap().length; y++)
        { sight[y] = data.getMap()[y].clone(); }
        
        fill('0');
    }
    
    public void update(int x, int y, boolean verbose)
    {
        if (verbose)
        {
            findVisable(x,y);
        } else {
            findVisableVerbose(x,y);
        }
    }
    
    /**
     * 2(x+y)*n calculations.
     * only searches for lines to the edges of the map.
     * a 40x80 map requires 240*n calculations.
     * 
     * inaccurate.
     * 
     * @param sx - start x pos.
     * @param sy - start y pos.
     */
    private void findVisable(int sx, int sy)
    {
        
        for (int ey = 0; ey < sight.length; ey++)
        {
            for (int ex = 0; ex < sight[ey].length; ex++)
            {
                if(ex == 0 || ey == 0 || ey == sight.length-1 || ex == sight[0].length-1)
                {
                    Line line = new Line(sx,sy,ex,ey);
                    
                    for (Point p : line.getPoints())
                    {
                        int x = p.getX();
                        int y = p.getY();
                        
                        if(Files.currentMap()[y][x] == '#' || Files.currentDisMap()[y][x] == '+')
                        {
                            sight[y][x] = '1';
                            break;
                        } else {
                            sight[y][x] = '1';
                        }
                    }
                }
            }
        }
    }
    
    /**
     * x*y*n calculations
     * Searches for lines to every coord on the current map.
     * A 40x80 map requires 3600*n calculations.
     * 
     * very accurate.
     * 
     * @param sx
     * @param sy 
     */
    private void findVisableVerbose(int sx, int sy)
    {
        
        for (int ey = 0; ey < sight.length; ey++)
        {
            for (int ex = 0; ex < sight[ey].length; ex++)
            {
                Line line = new Line(sx,sy,ex,ey);

                for (Point p : line.getPoints())
                {
                    int x = p.getX();
                    int y = p.getY();

                    if(Files.currentMap()[y][x] == '#' || Files.currentDisMap()[y][x] == '+')
                    {
                        sight[y][x] = '1';
                        break;
                    } else {
                        sight[y][x] = '1';
                    }
                }
            }
        }
    }
    
    private void findCircle(int px, int py, int radius)
    {
        
        for (int y = -radius; y < radius; y++)
        {
            for (int x = -radius; x < radius; x++)
            {
                int dist = (int) Math.round( Math.sqrt(x*x + y*y) );
                
                if ( dist < radius)
                {
                    try
                    {
                    sight[py+y][px+x] = '1';
                    } catch (IndexOutOfBoundsException e) { }
                }
            }
        }
    }
    
    private List findLine (int ex, int ey, int x, int y)
    {
        return new Line(x,y,ex,ey).getPoints();
    }
    
    private void fill (char a)
    {
        for (int y = 0; y < sight.length; y++)
        {
            for (int x = 0; x < sight[y].length; x++)
            {
                sight[y][x] = a;
            }
        }
    }
    
    private void print (char[][] array)
    {
        for (int y = 0; y < array.length; y++)
        {
            for (int x = 0; x < array[y].length; x++)
            {
                System.out.print( array );
            }
            System.out.println();
        }
    }
    
    public char[][] getVisible()
    {
        return sight;
    }
}