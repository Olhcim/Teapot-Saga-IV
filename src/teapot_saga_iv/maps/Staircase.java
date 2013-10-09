package teapot_saga_iv.maps;

import teapot_saga_iv.Main;


public class Staircase {

    int x, y;
    
    int destWorld, destLevel;
    
    
    public Staircase (int x, int y, int destWorld, int destLevel)
    {
        this.x = x;
        this.y = y;
        this.destWorld = destWorld;
        this.destLevel = destLevel;
    }
    
    private void use()
    {
        Main.world = destWorld;
        Main.level = 1;
        Main.getPlayer().goToStart();
    }
    
    public void checkForPlayer()
    {
        if (Main.getPlayer().getX() == x && Main.getPlayer().getY() == y)
        {
            Main.useMap(destWorld, destLevel);
        }
    }
    
    public char getSymbol()
    {
        if(destWorld > Main.world)
        {
            return '<';
        }
        else if(destWorld < Main.world)
        {
            return '>';
        }
        else if(destWorld == Main.world)
        {
            if(destLevel > Main.world)
            {
                return '<';
            }
            else if(destLevel < Main.world)
            {
                return '>';
            }
        }
        return '?';
    }
} 