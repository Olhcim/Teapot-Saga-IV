package teapot_saga_iv.characters;

import teapot_saga_iv.Files;
import teapot_saga_iv.Main;
import teapot_saga_iv.Render;
import teapot_saga_iv.maps.Staircase;


public class Player extends Character{
    
    private int moves = 0;
    private int healthPotions = 0;
    private boolean triedToUsePot = false;
    
    private int critChance = 10;
    
    public int allowedOverworldStaircase = 1;
    
    public int getMoves()
    { return moves; }
    
    public int getHealthPotions()
    { return healthPotions; }
    
    public void update()
    {
        moves++;
        checkForStairCase();
        System.out.println(allowedOverworldStaircase);
    }
    
    
    
    
    public void freeze()
    {
        canMove = false;
    }
    
    public void unfreeze()
    {
        canMove = true;
    }
    
    public boolean canMove()
    {
        return canMove;
    }
    
    
    
    
    public void giveHealthPotion(int a)
    {
        healthPotions += ((a > 0) ? a : 0);
        Render.queueDialog("You recieved " + a + " health potion" + ((a > 1) ? "s" : "") + "." );
    }
    
    public void useHealthPotion()
    {
        if (healthPotions > 0)
        {
            if(health > 99)
            {
                if (!triedToUsePot)
                {
                    triedToUsePot = true;
                Render.queueDialog("You cannot use health potions when you have full health");
                }
            } else {

                triedToUsePot = false;
                
                healthPotions--;
                int gain = (health > 75) ? ( 25 - (health+25-100) ) : 25;
                health += gain;

                Render.queueDialog("You used a health potion and gained " + gain + " life." );
            }
        } else {
            Render.queueDialog("You do not have any tea leaves to use in health potions." );
        }
        Render.updateDialog();
        Render.updateStats();
    }
    
    
    
    
    
    
    /**
     * Moves the player to the start but does not update the GUI.
     */
    public void goToStart()
    {
        setPos(Files.currentMapData().getStartX(), Files.currentMapData().getStartY());
    }
    /**
     * Moves the player to the exit but does not update the GUI.
     */
    public void goToExit()
    {
        setPos(Files.currentMapData().getExitX(), Files.currentMapData().getExitY());
    }
    

    
    public void attack(Monster m)
    {
        int crit = (int) (Math.random()*100);
        
        if (critChance > crit) {
            damage = 4;
            Render.queueDialog("You landed a critical strike and dealt 4 damage.");
        } else {
            damage = 2;
        }
        
        
        m.damage(damage);
    }
    
    /**
     * Checks weather the current position is over an entrance.
     */
    private boolean isAtEntrance()
    {
        return Files.currentMap()[y][x] == '<';
    }
    
    /**
     * Checks weather the current position is over an exit.
     */
    private boolean isAtExit()
    {
        return Files.currentMap()[y][x] == '>';
    }
    
    /**
     * recieves the keycodes of keys pressed from Window.class
     */
    public void handleKey(int a)
    {
        switch (a)
        {
            case 65:
//            case 37:
                if(canMove(x-1, y)) { x--; Main.doGameTick();}
                break;
            case 87:
//            case 38:
                if(canMove(x, y-1)) { y--; Main.doGameTick();}
                break;
            case 68:
//            case 39:
                if(canMove(x+1, y)) { x++; Main.doGameTick();}
                break;
            case 83:
//            case 40:
                if(canMove(x, y+1)) { y++; Main.doGameTick();}
                break;
            case 79:
                 useDoor(); Main.doGameTick();
                break;
            case 67:
                Main.doGameTick();
                break;
            case 49:
                Main.PrevMap();         //quick navigation, testing purposes only.
                break;
            case 50:
                Main.NextMap();         //quick navigation, testing purposes only.
                break;
            case 32:
                Render.paintDialogInQueue();         //quick navigation, testing purposes only.
                break;
            case 70:
                useHealthPotion();
                break;
        }
    }
    
    /**
     * checks for a door and switches the state of the door if it exists.
     */
    private void useDoor()
    {
        if(doorAt(0,1))
        {
            openDoor(0,1);
            openDoor(1,1);
            openDoor(-1,1);
            
        } else if (doorAt(0,-1))
        {
            openDoor(0,-1);
            openDoor(1,-1);
            openDoor(-1,-1);
        } else if (doorAt(1,0))
        {
            openDoor(1,0);
            openDoor(1,1);
            openDoor(1,-1);
        } else if (doorAt(-1,0))
        {
            openDoor(-1,0);
            openDoor(-1,1);
            openDoor(-1,-1);
        }
    }
    
    /**
     * Switches the state of a door between open and closed.
     * 
     * @param x The x position of the door
     * @param y The y position of the door
     */
    private void openDoor(int x, int y)
    {

        if      (Files.currentDisMap()[this.y+y][this.x+x] == '+')
        {
            Files.currentDisMap()[this.y+y][this.x+x] = 'X';
        }
        else if (Files.currentDisMap()[this.y+y][this.x+x] == 'X')
        {
            Files.currentDisMap()[this.y+y][this.x+x] = '+';
        }
        
    }
    
    /**
     * 
     * @param x The x position of the door
     * @param y The y position of the door
     * @return True or False if there is a door at the specified coordinates.
     */
    public boolean doorAt(int x, int y)
    {
        if(Files.currentDisMap()[this.y+y][this.x+x] == '+' || Files.currentDisMap()[this.y+y][this.x+x] == 'X')
        {
            return true;
        }
        return false;
    }
    
    /**
     * Checks weather the current position is over an entrance or exit and follows the appropriate actions..
     */
    private void checkForStairCase()
    {

        if(isAtExit())
        {
            Main.NextMap();
        }
        else if (isAtEntrance())
        {
            Main.PrevMap();
        }
        else
        {
            for (Staircase s : Files.currentMapData().getStairs())
            {
                
                if (Main.isAtOverworld)
                {
                    if (Integer.parseInt( s.getSymbol() + "" ) <= allowedOverworldStaircase)
                    {
                        System.out.println("Staircase: " + s.getSymbol() + " " + s.getX() + " " + s.getY());
                        if (s.getX() == x && s.getY() == y)
                        {
                            
                            Main.useMap(s.getDestWorld(), s.getDestLevel());
                        }
                    }
                }
                else if (s.getX() == x && s.getY() == y)
                {
                    Main.useMap(s.getDestWorld(), s.getDestLevel());
                }
            }
        }
    }
} 