package teapot_saga_iv.characters;


public class Monster_Golem extends Monster {

    public Monster_Golem(int x, int y)
    {
        this.x = x;
        this.y = y;
        startX = x;
        startY = y;
        
        damage = 1;
        health = 10;
        
        name = "Golem";
        symbol = 'G';
    }
} 