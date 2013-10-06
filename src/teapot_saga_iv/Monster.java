package teapot_saga_iv;


public class Monster extends Character{

    char symbol;
    
    public static final String DEFAULT = "default";
    public static final String ZOMBIE = "zombie";
    public static final String SPIDER = "spider";
    
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
        if (distanceToPlayer() > 20)
        {
            displace(true);
        }
        else if (distanceToPlayer() >= 2.5 && distanceToPlayer() <= 20)
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
                displace(true);
            }
        }
        else if (distanceToPlayer() < 1)
        {
                displace(false);
        }

    }
    
    public void move(int disX, int disY)
    {
        if (disX > -2 && disX < 2 && disY > -2 && disY < 2)
        {
            setPos(x+disX, y+disY);
        }
    }
    
    public void move(int dir)
    {
        
        if (canMove(dir))
        {
            switch (dir)
            {
                // N:0  - E:2  - S:4  - W:6
                case 0: setPos(x, y-1); break;
                case 2: setPos(x+1, y); break;
                case 4: setPos(x, y+1); break;
                case 6: setPos(x-1, y); break;
                // NE:1 - SE:3 - SW:5 - NW:7
                case 1: setPos(x+1, y-1); break;
                case 3: setPos(x+1, y+1); break;
                case 5: setPos(x-1, y+1); break;
                case 7: setPos(x-1, y-1); break;
            }
        }
    }
    
    public void displace(boolean diagonal)
    {
        int ran;
        
        do
        {
            if (diagonal)
            {
            ran = (int) (Math.random()*8);
            } else {
                ran = (int) (Math.random()*4) * 2;
            }
        } while (!canMove(ran));
        move(ran);
    }
    
    public static boolean isMonster(int x, int y)
    {
        for (Monster m : Files.currentMapData().monsters)
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
        return Files.currentMap()[y][x] != '#' && Files.currentDisMap()[y][x] != '+' && canMove == true && !Monster.isMonster(x,y) && !Main.p.sameAsPlayer(x,y);
    }
    
} 