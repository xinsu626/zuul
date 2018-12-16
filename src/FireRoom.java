import java.util.ArrayList;

public class FireRoom extends Room
{
    private String exitdir;
    private ArrayList<Item> inventory;

    public FireRoom(String exitdir, String desc, ArrayList<Item> inventory) {
        super(desc);
        this.exitdir = exitdir;
        this.inventory = inventory;
    }

    public String getShortDescription() {
        if (Extinguisher.hasExtinguisher(inventory)) {
            return super.getShortDescription();
        }
        String desc = "You have entered a room that is on fire, you need a fire extinguisher!";
        return desc;
    }

    public String getLongDescription() {
        if (Extinguisher.hasExtinguisher(inventory)) {
            return super.getLongDescription();
        }
        String desc1 = getShortDescription();
        String desc2 = "The only direction that can leave is " + exitdir;
        return desc1 + "\n" + desc2;
    }

    public Room respond(Command c, ArrayList<Item> inventory) {
        String commandWord = c.getCommandWord();  //返回命令的第一个单词
        String object = c.getSecondWord();  //返回命令的第二个单词

        if (commandWord.equals("go")
                && !object.equals(exitdir)
                && !Extinguisher.hasExtinguisher(inventory) )
        {
            System.out.println("The fire is too big, you can't see any exit!");
            return null;
        }

        if (commandWord.equals("open")) {
            Item f = Item.findByName(object, inventory);
            if (f == null) {
                System.out.println("you don't have the " + object);
                return null;
            }
            if (f instanceof Extinguisher) {
                Extinguisher fl = (Extinguisher) f;
                fl.open();
                System.out.println("You turned on the " + object + " and used it to extinguish the fire.");
                System.out.println(getLongDescription());
                return null;
            } else {
                System.out.println("You can't open the " + object + "!");
                return null;
            }

        }

        if (commandWord.equals("take")) {
            if (Extinguisher.hasExtinguisher(inventory)) {
                return super.respond(c, inventory);
            } else {
                System.out.println("The fire is too big, you can't take anything！");
                return null;
            }
        }
        return super.respond(c, inventory);

    }
}
