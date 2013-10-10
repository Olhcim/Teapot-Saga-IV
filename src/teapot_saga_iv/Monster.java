package teapot_saga_iv;


public class Monster extends Character{

    char symbol;
    
    public static final String DEFAULT = "default";
    public static final String PRISONER = "zombie";
    public static final String ZOMBIE = "zombie";
    public static final String GOLEM = "golem";
    
    int startX, startY;
    
    public Monster(int x, int y, int health, char symbol)
    {
        this.x = x;
        this.y = y;
        this.startX = x;
        this.startY = y;
        this.health = health;
        this.symbol = symbol;
    }
    
    public Monster(int x, int y, String type)
    {
        this.x = x;
        this.y = y;
        this.startX = x;
        this.startY = y;
        
        if (ZOMBIE.equalsIgnoreCase(type))
        {
            this.health = 5;
            this.symbol = 'Z';
        } else if (GOLEM.equalsIgnoreCase(type))
        {
            this.health = 10;
            this.symbol = 'G';
        } else if (PRISONER.equalsIgnoreCase(type))
        {
            this.health = 2;
            this.symbol = 'P';
        } else {
            this.health = 25;
            this.symbol = 'D';
        }
    }
    
    public void moveTowardsPlayer()
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
                findPath(Main.p.getX(), Main.p.getY());

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
    
    public void move(int disX, int disY)
    {
        if (disX > -2 && disX < 2 && disY > -2 && disY < 2)
        {
            setPos(x+disX, y+disY);
        }
    }
    
    public void displace()
    {
        do
        {
            int x = (int) (Math.random()*3) - 1;
            int y = (int) (Math.random()*3) - 1;
            
            if (canMove(this.x + x, this.y + y))
            {
                move(x, y);
                break;
            }
        } while (true);
    }
    
    public static boolean isMonster(int x, int y)
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
        
        if (health < 1)
        {
            Files.currentMapData().getMonsters().remove(this);
        }
    }
    
    @Override
    public boolean canMove(int x, int y)
    {
        return Files.currentMap()[y][x] != '#' && Files.currentDisMap()[y][x] != '+' && canMove == true && !Monster.isMonster(x,y) && !Main.p.sameAsPlayer(x,y);
    }
    
} 