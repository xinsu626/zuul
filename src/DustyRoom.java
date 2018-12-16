import java.util.ArrayList;

public class DustyRoom extends Room
{
    private boolean is_clean = false;

    public DustyRoom(String description)
    {
        super(description);
    }

    public Room respond(Command c, ArrayList<Item> inventory) {
        String commandWord = c.getCommandWord();
        String object = c.getSecondWord();
        if (commandWord.equals("sweep")) {
            Item broom = Item.findByName("broom", inventory);
            if (broom == null) {
                System.out.println("You can't sweep without a broom!");
                return null;
            }
            // now we know we have the broom
            System.out.println("A magic wand appears under the dust");
            is_clean = true;
            addItem(new Item("wand"));
            return null;
        }
        return super.respond(c, inventory);
    }

}