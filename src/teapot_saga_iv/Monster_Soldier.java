package teapot_saga_iv;


public class Monster_Soldier extends Monster {

    public Monster_Soldier(int x, int y)
    {
        this.x = x;
        this.y = y;
        startX = x;
        startY = y;
        
        damage = 0;
        
        health = (int) (Math.random()*3 + 3);
        
        name = "Soldier";
        symbol = 224;
    }
    
    @Override
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
            damage = (int) (Math.random()*3 + 1);
            
            Main.getPlayer().damage(damage);
            Render.queueDialog("You were delt " + damage + " damage by a " + name + ".");
        }
    }
} 