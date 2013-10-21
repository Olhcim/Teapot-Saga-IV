package teapot_saga_iv;

public class Monster_Teageist extends Monster {

    public Monster_Teageist(int x, int y)
    {
        this.x = x;
        this.y = y;
        startX = x;
        startY = y;
        
        damage = 2;
        health = 4;
        
        name = "Teageist";
        symbol = 226;
    }
} 