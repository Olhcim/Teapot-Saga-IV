package teapot_saga_iv.characters;

import teapot_saga_iv.Files;
import teapot_saga_iv.Main;
import teapot_saga_iv.Render;
import teapot_saga_iv.a_star.AStar;

public class Monster extends Character{
    
    int turnsNextToPlayer;
    
    int startX, startY;
    
    
    public void update()
    {
        moveTowardsPlayer();
        damagePlayer();
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
            Render.queueDialog("You were delt " + damage + " damage by a " + name + ".");
        }
    }
    
    
    void moveTowardsPlayer()
    {
        double distanceToPlayer = distanceToPlayer();
        
        if (distanceToPlayer > 20)
        {
            findPath(startX, startY);
            
            if (path != null)
            {
                path = path.getParent();

                    setPos(path.getX(), path.getY());
            }
            else
            {
                displace();
            }
        }
        else
        {
            if (distanceToPlayer >= 2.5 )
            {
                findPath(Main.getPlayer().getX(), Main.getPlayer().getY());

                if (path == null)
                {
                    findPath(startX, startY);
                }

                if (path != null)
                {
                    path = path.getParent();

                        setPos(path.getX(), path.getY());
                }
                else
                {
                    displace();
                }
            }
            else if (distanceToPlayer < 1)
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
    
    public double distanceToPlayer()
    {
        int px = Main.getPlayer().getX();
        int py = Main.getPlayer().getY();
        
        return Math.sqrt((x-px)*(x-px) + (y-py)*(y-py));
    }
    
    
    
    public void findPath(int x, int y)
    {
        path = new AStar(this.x, this.y, x, y).getFinalNode();
    }
    
    @Override
    public void damage(int d)
    {
        health -= d;
        
        System.out.println(getType() + " Health: " + health);
        
        if (health < 1)
        {
            Files.currentMapData().queueEntityForRemoval(this);
            Render.queueDialog (
                    "You attacked a " + name + " and killed it."
                    + "\n\nDamage dealt: " + d
                    + "\n" + name + " health left: " + health );
        } else {
            Render.queueDialog (
                    "You attacked a " + name + "."
                    + "\n\nDamage dealt: " + d
                    + "\n" + name + " health left: " + health );
        }
    }
    
    @Override
    public boolean canMove(int x, int y)
    {
        return Files.currentMap()[y][x] != '#' && Files.currentDisMap()[y][x] != '+' && canMove == true && !Monster.isEntity(x,y) && !isOverPlayer(x,y);
    }
    
} 