package teapot_saga_iv;


public class Player {
    
    public int health = 10;
    public static int x, y;
    public static char gender = '♂';

    public Player()
    {
        x=3; y=3;
    }
    
    public static void move(int a)
    {
            switch (a)
            {
                case 37:
                    System.out.println("Left ");
                        if(Maps.map[y][x-1] == '.') { x--; }
                    break;
                    
                case 38:
                    System.out.println("Up ");
                        if(Maps.map[y-1][x] == '.') { y--; }
                    break;
                    
                case 39:
                    System.out.println("Right ");
                        if(Maps.map[y][x+1] == '.') { x++; }
                    break;
                    
                case 40:
                    System.out.println("Down ");
                        if(Maps.map[y+1][x] == '.') { y++; }
                    break;
            }
    }
} 