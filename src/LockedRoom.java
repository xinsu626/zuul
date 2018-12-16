import java.util.ArrayList;


public class LockedRoom extends Room
{
    private boolean lock = true;
    private String locked_dir;
    private ArrayList<Item> inventory;


    public LockedRoom(String description, String ldir)
    {
        super(description);
        locked_dir = ldir;
        this.inventory = inventory;
    }

    public Room respond(Command c, ArrayList<Item> inv) {
        String commandWord = c.getCommandWord();
        String direction = c.getSecondWord();
        if (commandWord.equals("go")
                && direction!= null
                && direction.equals(locked_dir)
                && Item.findByName("key", inv) == null)
        {
            System.out.println("Hm, the door seems to be locked");
            return null;
        }
        return super.respond(c, inv);
    }

}
