package teapot_saga_iv;

public class Render
{
    
    public static String render;

    public Render()
    {
        
        
        while(Main.gameActive)
        {
            char[][] map = Maps.map;
            render = "";
            int px = Player.x;
            int py = Player.y;
            
            for(int y = 0; y < map.length; y++)
            {
                for (int x = 0; x < map[y].length; x++)
                {
                    
                    if(px==x & py==y)
                    {
                        render+="@";
                        
                    } else {
                        render+=map[y][x];
                    }
                    
                }
                render+="\n";
            }
            
            Window.setText(render);
            
        }
        
    }
} 