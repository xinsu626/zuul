import java.util.ArrayList;

public class DragonRoom extends Room
{
    private boolean dragon_deaded = false;
    private boolean strong = false;

    public DragonRoom(String description) {
        super(description);
    }

    public void eatRespond(String object, ArrayList<Item> inventory) {
        if (object == null){
            System.out.println("eat what?");
            return;
        }

        Item f = Item.findByName(object, inventory);
        if (f == null) {
            System.out.println("You don't have " + object);
            return;
        }
        if (f instanceof Edible) {
            inventory.remove(f);
            Edible et = (Edible) f;
            et.eat();
            if (et.getEat_times() == 1) {
                System.out.println("you feel a little bit stronger; keep eating!");
                inventory.add(et);
                return;
            } else {
                System.out.println("you feel much stronger");
                strong = true;
                inventory.remove(et);
                return;
            }
        }
        System.out.println("you can't eat that");
    }

    //fight
    public void fightRespond(String object, ArrayList<Item> inventory) {
        if (object.equals("sword")) {
            Item swordItem = Item.findByName("sword", inventory);
            if (swordItem == null) {
                System.out.println("You don't have sword. You cannot fight with the dragon! when you have sword, you should input fight_with sword");
                return;
            }

            if (!strong){
                System.out.println("You are too weak to fight! You should pick up the chocolate and eat it.");
                return;
            }

            System.out.println("You used the sword and killed the drag");
            System.out.println("You now see a extinguisher that had been underneath the dragon");
            dragon_deaded = true;
            setDescription("in the library");
            addItem(new Extinguisher("extinguisher", 6));
            return;
        }
        System.out.println("You can't fight with the dragon! You should eat some chocolate and find a sword to fight!");

    }


    public Room respond(Command c, ArrayList<Item> inventory) {
        String commandWord = c.getCommandWord();
        String object = c.getSecondWord();      // or direction

        // first command is go
        if (commandWord.equals("go")
                && !dragon_deaded
                && object != null){
            System.out.println("The room has been locked. Eat some chocolate and use your sword fight with the dragon.");
            return null;
        }

        // first command is eat
        if (commandWord.equals("eat")) {
            eatRespond(object, inventory);
            return null;
        }

        // first command is fight_with
        if (commandWord.equals("fight_with")) {
            fightRespond(object, inventory);
            return null;
        }

        // TAKE
        if (commandWord.equals("take") && object.equals("dragon")) {
            System.out.println("the dragon is WAAAY too big to pick up");
            return null;
        }

        return super.respond(c, inventory);

    }
}
