
package teapot_saga_iv.characters;

public class Item extends Entity {
    
    @Override
    public void update()
    {
        if (isOverPlayer(x,y))
        {
            doPickupAction();
        }
    }
    
    public void doPickupAction() {}
    
}
