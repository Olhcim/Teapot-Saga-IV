package teapot_saga_iv;


public class Player {
    
    public int health = 10;
    public static int x=2, y=2;
    
    public static void setPos(int a, int b)     //sets the possition of the player when 
    {
        x = a;
        y = b;
    }
    
    public static void move(int a)  //recieves code of key pressed from Window.class
    {
        switch (a)
        {
            case 65:
            case 37:
                if(Files.map[y][x-1] != '#') { x--; }
                System.out.println(" Dir: Left");
                Main.doGameTick();
                break;
            case 87:
            case 38:
                if(Files.map[y-1][x] != '#') { y--; }
                System.out.println(" Dir: Up");
                Main.doGameTick();
                break;
            case 68:
            case 39:
                if(Files.map[y][x+1] != '#') { x++; }
                System.out.println(" Dir: Right");
                Main.doGameTick();
                break;
            case 83:
            case 40:
                if(Files.map[y+1][x] != '#') { y++; }
                System.out.println(" Dir: Down");
                Main.doGameTick();
                break;
        }
        
        if(Files.map[y][x] == '>')
        {
            Main.NextMapAndUpdate();
        }
    }
} 