
package teapot_saga_iv;

public class Item_HealthPotion extends Item{
    
    public Item_HealthPotion(int x, int y)
    {
        this.x = x;
        this.y = y;
        
        name = "Health Potion";
        symbol = 240;
    }
    
    @Override
    public void doPickupAction()
    {
        Main.getPlayer().giveHealthPotion(1);
        Files.currentMapData().queueEntityForRemoval(this);
    }
    
}
