package teapot_saga_iv;

public class Monster_TeageistMinion extends Monster {

    public Monster_TeageistMinion(int x, int y)
    {
        this.x = x;
        this.y = y;
        startX = x;
        startY = y;
        
        damage = 2;
        health = 4;
        
        name = "Minion of the Teanity";
        symbol = 226;
    }
    
    @Override
    public void update()
    {
        boolean move = ((int) (Math.random()*3) != 0) ? true : false;
        
        if (move)
        {
            moveTowardsPlayer();
            damagePlayer();
        } else {
            
            if (distanceToPlayer() < 1)
            {
                Main.getPlayer().attack(this);
            }
            displace();
            damagePlayer();
        }
        
    }
} 