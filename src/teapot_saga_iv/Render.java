package teapot_saga_iv;

public class Render
{
    
    public static String render;

    public static void rend()
    {

        render = "";

            
        for(int y = 0; y < Files.map.length; y++)
        {
            for (int x = 0; x < Files.map[y].length; x++)
            {
                    
                if(Player.x==x & Player.y==y)
                {
                    render+="@";
                    System.out.print("Pos: " + Player.x + " " + Player.y);
                    System.out.print(" Tile: " + Files.map[Player.y][Player.x]);
                    
                } else {
                    render+=Files.map[y][x];
                }
                    
            }
            render+="\n";
        }
            
        Window.setText(render);
    }
    
    
} 