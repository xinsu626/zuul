import java.util.ArrayList;


public class DarkRoom extends Room
{
    private String exitdir;
    private ArrayList<Item> inventory;


    public DarkRoom(String exitdir, String desc, ArrayList<Item> inv) {
        super(desc);
        this.exitdir = exitdir;
        this.inventory = inv;
    }

    public String getShortDescription() {
        if (Light.haslight(inventory))
            return super.getShortDescription();
        String desc = "in a pitch-dark place! You need a light!";
        return desc;
    }

    public String getLongDescription() {
        if (Light.haslight(inventory))
            return super.getLongDescription();
        String desc1 = getShortDescription();
        String desc2 = "The only way out you can see is a dimly lit exit to the " + exitdir;
        return desc1 + "\n" + desc2;
    }


    public Room respond(Command c, ArrayList<Item> inventory) {
        String commandWord = c.getCommandWord();
        String object = c.getSecondWord();

        if (commandWord.equals("go")
                && !object.equals(exitdir)
                && !Light.haslight(inventory) )
        {
            System.out.println("It's too dark to see any exit that way!");
            return null;
        }


        if (commandWord.equals("light")) {
            Item f = Item.findByName(object, inventory);
            if (f == null) {
                System.out.println("you don't have the " + object);
                return null;
            }
            if (f instanceof Light) {
                Light fl = (Light) f;
                fl.light();
                System.out.println("The " + object + " is now lit");
                System.out.println(getLongDescription());
                return null;
            } else {
                System.out.println("You can't light the " + object + "!");
                return null;
            }

        }

        if (commandWord.equals("take")) {
            if (Light.haslight(inventory)) {
                return super.respond(c, inventory);
            } else {
                System.out.println("It's too dark to see anything to take!");
                return null;
            }
        }

        return super.respond(c, inventory);

    }
}