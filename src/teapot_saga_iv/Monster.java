package teapot_saga_iv;


public class Monster extends Character{

    char symbol;
    
    public static final String DEFUALT = "DEFUALT"; 
    
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
        
        if (type == DEFUALT)
        {
            this.health = 10;
            this.symbol = 'M';
        }
    }
    
    public void moveTowards(int x, int y)
    {
        if(x <= this.x && canMove(this.x - 1, this.y))
        {
            setPos(this.x - 1, this.y);
        }
        if(x >= this.x && canMove(this.x + 1, this.y))
        {
            setPos(this.x + 1, this.y);
        }
        if(y <= this.y && canMove(this.x, this.y - 1))
        {
            setPos(this.x, this.y - 1);
        }
        if(y >= this.y && canMove(this.x, this.y + 1))
        {
            setPos(this.x, this.y + 1);
        }
    }
    
    @Override
    public boolean canMove(int x, int y)
    {
        boolean notOnPlayer = Main.p.x != x && Main.p.y != y;
        
                
        return Files.map[y][x] != '#' && Files.disMap[y][x] != '+' && notOnPlayer;
    }
} 