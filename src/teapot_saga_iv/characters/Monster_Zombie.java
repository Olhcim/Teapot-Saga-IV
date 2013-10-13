package teapot_saga_iv.characters;


public class Monster_Zombie extends Monster {

    public Monster_Zombie(int x, int y)
    {
        this.x = x;
        this.y = y;
        startX = x;
        startY = y;
        
        damage = 2;
        health = 4;
        
        name = "Zombie";
        symbol = 'Z';
    }
} 