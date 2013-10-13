package teapot_saga_iv.characters;

import teapot_saga_iv.Files;
import teapot_saga_iv.Main;

public class Monster extends Character{
    
    int turnsNextToPlayer;
    
    String name;
    char symbol;
    int startX, startY;
    
    
    public void update()
    {
        moveTowardsPlayer();
        damagePlayer();
    }
    
    
    public String getType()
    {
        return name;
    }
    
    public char getSymbol()
    {
        return symbol;
    }
    
    
    
    void damagePlayer()
    {
        if (distanceToPlayer() < 2)
        {
            turnsNextToPlayer++;
        } else {
            turnsNextToPlayer = 0;
        }
        
        if (turnsNextToPlayer > 1)
        {
            Main.getPlayer().damage(damage);
        }
    }
    
    
    void moveTowardsPlayer()
    {
        if (distanceToPlayer() > 20)
        {
            findPath(startX, startY);
            
            if (path != null)
            {
                path = path.getParent();

                if (!isMonster(path.getX(), path.getY()))
                {
                    setPos(path.getX(), path.getY());
                } 
            }
            else
            {
                displace();
            }
        }
        else
        {
            if (distanceToPlayer() >= 2.5 )
            {
                findPath(Main.getPlayer().getX(), Main.getPlayer().getY());

                if (path == null)
                {
                    findPath(startX, startY);
                }

                if (path != null)
                {
                    path = path.getParent();

                    if (!isMonster(path.getX(), path.getY()))
                    {
                        setPos(path.getX(), path.getY());
                    } 
                }
                else
                {
                    displace();
                }
            }
            else if (distanceToPlayer() < 1)
            {
                    Main.getPlayer().attack(this);
                    displace();
            }
        }

    }
    
    void move(int disX, int disY)
    {
        if (disX > -2 && disX < 2 && disY > -2 && disY < 2)
        {
            setPos(x+disX, y+disY);
        }
    }
    
    void displace()
    {
        do
        {
            int dx = (int) (Math.random()*3) - 1;
            int dy = (int) (Math.random()*3) - 1;
            
            if (canMove(this.x + dx, this.y + dy))
            {
                move(dx, dy);
                break;
            }
        } while (true);
    }
    
    static boolean isMonster(int x, int y)
    {
        for (Monster m : Files.currentMapData().getMonsters())
        {
            if (m.x == x && m.y == y)
            {
                return true;
            }
        }
        return false;
    }
    
    
    
    @Override
    public void damage(int d)
    {
        health -= d;
        
        System.out.println(getType() + " Health: " + health);
        
        if (health < 1)
        {
            Files.currentMapData().killMonster(this);
        }
    }
    
    @Override
    public boolean canMove(int x, int y)
    {
        return Files.currentMap()[y][x] != '#' && Files.currentDisMap()[y][x] != '+' && canMove == true && !Monster.isMonster(x,y) && !Main.getPlayer().sameAsPlayer(x,y);
    }
    
} 