package teapot_saga_iv;

public class Render
{
    
    public static String render;
    private static char[][] map;
    
    public static void loadMap()
    {
        map = Maps.map;
    }

    public static void rend()
    {

        render = "";

            
        for(int y = 0; y < map.length; y++)
        {
            for (int x = 0; x < map[y].length; x++)
            {
                    
                if(Player.x==x & Player.y==y)
                {
                    render+="@";
                        
                } else if (Maps.exitX==x & Maps.exitY==y)
                {
                    render+="X";
                } else {
                    render+=map[y][x];
                }
                    
            }
            render+="\n";
        }
            
        Window.setText(render);
    }
    
    
} 