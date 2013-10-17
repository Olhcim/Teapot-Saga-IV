package teapot_saga_iv.characters;

import teapot_saga_iv.Files;
import teapot_saga_iv.Main;
import teapot_saga_iv.Render;


public class Monster_TeageistBoss extends Monster {

    private int counter = 0;
    
    public Monster_TeageistBoss(int x, int y)
    {
        this.x = x;
        this.y = y;
        startX = x;
        startY = y;
        
        damage = 1;
        health = 25;
        
        name = "Teageist Boss";
        symbol = 231;
    }
    
    @Override
    public void update()
    {

        if (Files.currentMapData().isEntity("Teageist"))
        {
            counter = 0;
        }
        else
        {
            counter++;
            if (counter > 20)
            {
                spawnMinions();
                
                if (Main.getPlayer().getHealth() < 10)
                {
                    int px = (int) ((Math.random()*19)+3);
                    int py = (int) ((Math.random()*19)+3);
                    Files.currentMapData().addEntity(new Item_HealthPotion(px,py));
                }
            }
            
            if (isOverPlayer(x,y))
            {
                Main.getPlayer().attack(this);
                displace();
            } else {
                moveTowards(11,11);
                damagePlayer();
            }
        }
        
        if (isDead())
        {
            Files.currentMapData().getEntities().clear();
            Files.currentMapData().getMap()[11][1] = '<';
            Files.currentMapData().getMap()[11][21] = '>';
            Files.currentMapData().rerender();
            Render.updatMap();
            
            
            Main.endGame("You defeated the infamous Teageist, and completed the game, would you like to play again?");
        }
    }
    
    public void spawnMinions()
    {
        Files.currentMapData().addEntity(new Monster_Bat(1,11));
        Files.currentMapData().addEntity(new Monster_Bat(21,11));
        Files.currentMapData().addEntity(new Monster_Bat(11,1));
        Files.currentMapData().addEntity(new Monster_Bat(11,21));
        
        Render.queueDialog("A new wave of four Teageists has spawned.");
        Render.update();
    }

    void moveTowards(int destX, int destY)
    {
        
        if ( !(x == destX && y == destY) )
        {
            findPath(destX, destY);

            if (path != null)
            {
                path = path.getParent();
                
                if (canMove(path.getX(), path.getY()))
                {
                setPos(path.getX(), path.getY());
                }
                else
                {
                displace();
                }
            }
            else
            {
                displace();
            }
            
        }
        else
        {
            displace();
        }

            
    }

                
}

class Teageist extends Monster
{
    public Teageist(int x, int y)
    {
        this.x = x;
        this.y = y;
//        startX = x;
//        startY = y;
        
        damage = 2;
        health = 4;
        
        name = "Teageist";
        symbol = 226;
    }
}