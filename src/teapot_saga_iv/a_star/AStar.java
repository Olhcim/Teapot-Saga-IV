package teapot_saga_iv.a_star;

import java.util.ArrayList;
import java.util.List;
import teapot_saga_iv.Files;
import teapot_saga_iv.characters.Monster;

public class AStar {

    private int endX, endY;
    
    private Node start;
    private Node end;
    private Node path = null;
    
    boolean foundPath = false;
    
    Node current;
    Node lowest;
    
    List<Node> open   = new ArrayList<Node>();
    List<Node> closed = new ArrayList<Node>();
    List<Node> adj = new ArrayList<Node>();
    

    public AStar(int fromX, int fromY, int toX, int toY)
    {
        this.endX = fromX;
        this.endY = fromY;
        
        end = new Node(null, fromX,fromY, endX, endY, 0);
        start = new Node(null, toX ,toY, endX, endY, 0);

        lowest = start;
        closed.add(lowest);
        findAdj(lowest);
        
        while(true)
        {

            findLowest();
            findAdj(lowest);
            
            if (foundPath()) { break; }
            if (open.isEmpty()) { break; }
        }
            
    }
    
    /**
     * Checks if a path has been found.
     * @return 
     */
    private boolean foundPath()
    {
        for (Node n : open)
            {
                if(n.equal(end)) 
                {
                    path = n;
                    foundPath = true;
                    
                    //System.out.println("closed length: " + closed.size() + " open length: " + open.size());
                    
                    return true;
                }
            }
        return false;
    }
    
    /**
     * Finds the newest cheapest node in the open list.
     * 
     * Removes this node and then adds it to the closed list.
     */
    private void findLowest()
    {
        int low = Integer.MAX_VALUE;
        
        for(Node n : open)
        {
            if (n.getF() <= low)
            {
                low = n.getF();
                this.lowest = n;
            }
        }
        
        open.remove(this.lowest);
        closed.add(this.lowest);
    }
    
    /**
     * Finds the adjacent nodes to the current lowest node.
     * @param n 
     */
    private void findAdj(Node n)
    {        
        int x = n.x;
        int y = n.y;
        
        adj.clear();
        
        adj.add(new Node(n, x+1, y, endX, endY, 10));
        adj.add(new Node(n, x-1, y, endX, endY, 10));
        adj.add(new Node(n, x, y+1, endX, endY, 10));
        adj.add(new Node(n, x, y-1, endX, endY, 10));
        
        adj.add(new Node(n, x+1, y+1, endX, endY, 14));
        adj.add(new Node(n, x+1, y-1, endX, endY, 14));
        adj.add(new Node(n, x-1, y+1, endX, endY, 14));
        adj.add(new Node(n, x-1, y-1, endX, endY, 14));
        
        updateAdj();
    }
    
    /**
     * removes any invalid adjacent nodes.
     * 
     * adds valid adjacent nodes to the open list.
     */
    private void updateAdj()
    {
        List<Node> aTemp = new ArrayList<Node>();
        
        for (Node n : adj)
        {
            
            if (!removeNode(n))
            {
                aTemp.add(n);
            }
        }
        
        for (Node n : aTemp)
        {
            open.add(n);
        }

    }
    
    private boolean removeNode(Node n)
    {
        boolean remove =
                (   
                    Files.currentMap()[n.y][n.x] == '#'
                    ||  Files.currentDisMap()[n.y][n.x] == '+'
                    ||  containedInClosed(n)
                    ||  containedInOpen(n)
                    ||  isOtherMonster(n)
                );
        
        return remove;
    }
    
    public boolean isOtherMonster(Node n)
    {
        for (Monster m : Files.currentMapData().getMonsters())
        {
            if (m.getX() != endX && m.getY() != endY)
            {
                if (m.getX() == n.x && m.getY() == n.y)
                {
                    return true;
                }
            }
        }

        return false;
    }
   
    private boolean containedInClosed(Node n)
    {
        for (Node i : closed)
        {
            if (i.equal(n))
            {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean containedInOpen(Node n)
    {
        for (Node i : open)
        {
            if (i.equal(n))
            {
                if (i.getF() > n.getF())
                {
                    i.parent = n.parent;
                }
                return true;
            }
        }
        
        return false;
    }
    
    public Node getFinalNode()
    {
        if (foundPath)
        {
        return path;
        } else {
            return null;
        }
    }
    
    public int getEndX()
    {
        return endX;
    }
    
    public int getEndY()
    {
        return endY;
    }
} 