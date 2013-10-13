package teapot_saga_iv.maps;

import teapot_saga_iv.Files;
import teapot_saga_iv.Main;


public class Staircase {

    int x, y;
    
    int destWorld, destLevel;
    
    char symbol;
    
    
    public Staircase (int x, int y, int destWorld, int destLevel, char symbol)
    {
        this.x = x;
        this.y = y;
        this.destWorld = destWorld;
        this.destLevel = destLevel;
        this.symbol = symbol;
    }
    
    public int getX()
    { return x; }
    public int getY()
    { return y; }
    
    public int getDestWorld()
    { return destWorld; }
    public int getDestLevel()
    { return destLevel; }
    
    public char getSymbol()
    {
        return symbol;
    }
} 