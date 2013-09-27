package teapot_saga_iv;


public class Monster extends Character{

    char symbol;
    
    public static final String DEFAULT = "default";
    public static final String ZOMBIE = "zombie";
    public static final String SPIDER = "spider";
    
    public Monster(int x, int y, int health, char symbol)
    {
        this.x = x;
        this.y = y;
        this.health = health;
        this.symbol = symbol;
    }
    
    public Monster(int x, int y, String type)
    {
        this.x = x;
        this.y = y;
        
        if (ZOMBIE.equalsIgnoreCase(type))
        {
            this.health = 50;
            this.symbol = 'Z';
        } else if (SPIDER.equalsIgnoreCase(type))
        {
            this.health = 10;
            this.symbol = 'S';
        } else {
            this.health = 25;
            this.symbol = 'D';
        }
    }
    
    public void moveTowardsPlayer()
    {
        if (distanceToPlayer() < 1)
        {
            displace();
        }
        else if (distanceToPlayer() > 3)
        {
            findPath(Main.p.getX(), Main.p.getY());
        
            if (path != null && path.getParent() != null)
            {
                path = path.getParent();

                if (!isMonster(path.getX(), path.getY()))
                {
                setPos(path.getX(), path.getY());
                }
            } else {
                displace();
            }
        }

    }
    
    public static boolean isMonster(int x, int y)
    {
        for (Monster m : Files.monsters)
        {
            if (m.x == x && m.y == y)
            {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean canMove(int x, int y)
    {
        return Files.map[y][x] != '#' && Files.disMap[y][x] != '+' && canMove == true && !Monster.isMonster(x,y);
    }
    
} 