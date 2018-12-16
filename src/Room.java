import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;


//The rooms are connected by an exit, and each exit is a pointer to another room.
public class Room
{
    private String description;
    private HashMap<String,Room> exits;
    private ArrayList<Item> items;


    public Room(String description) {
        this.description = description;
        exits = new HashMap<String,Room>();
        items = new ArrayList<Item>();
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public String getShortDescription() {
        return description;
    }

    public void setDescription(String s) {
        description = s;
    }

    public String getLongDescription() {
        String ldesc =  "You are " + getShortDescription() + ".\n" + getExitString();
        if (items.size() > 0) {
            ldesc += "\nThe following things are here:\n";
            for (int n = 0; n < items.size(); n++) {
                Item item = items.get(n);
                ldesc += "\t" + item.getDesc() + "\n";
            }
        }
        return ldesc;
    }

    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String direction : keys) {
            returnString += " " + direction;
        }
        return returnString;
    }

    public Room getExit(String direction)
    {
        Room newroom = exits.get(direction);
        if (newroom == null) {
            System.out.println("There is no door that way!");
        }
        return newroom;
    }

    public void addItem(Item i) {
        items.add(i);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    private Room goRoom(Command command, ArrayList<Item> inv)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return null;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
            return null;
        } else {
            System.out.println(nextRoom.getLongDescription());
            return nextRoom;
        }
    }

    public void doDrop(Command c, ArrayList<Item> inventory) {
        if (! c.hasSecondWord()) {  // "DROP", no object named
            System.out.println("Drop what?");
            return;
        }
        String s = c.getSecondWord();
        Item i = Item.findByName(s, inventory);
        if (i == null) {
            System.out.println("You don't have a " + s);
            return;
        }
        inventory.remove(i);
        //currentRoom.addItem(i);
        getItems().add(i);      // was currentRoom.getItems()
        System.out.println("You have dropped the " + s);
    }

    public void doTake(Command c, ArrayList<Item> inventory) {
        if (! c.hasSecondWord()) {  // "TAKE", no object named
            System.out.println("Drop what?");
            return;
        }
        String s = c.getSecondWord();
        ArrayList<Item> rinventory = getItems();
        Item i = Item.findByName(s, rinventory);
        if (i == null) {
            System.out.println("There isn't a " + s + " here.");
            return;
        }
        rinventory.remove(i);
        inventory.add(i);
        System.out.println("You have taken the " + s);
    }

    public Room respond(Command c, ArrayList<Item> inventory) {
        String commandWord = c.getCommandWord();
        String object = c.getSecondWord();
        if (commandWord.equals("go")) {
            return goRoom(c, inventory);
        }
        if (commandWord.equals("take")) {
            doTake(c, inventory);
        } else if (commandWord.equals("drop")) {
            doDrop(c, inventory);
        } else {            // pld catchall
            System.out.println("Hmm... I don't seem to know how to "
                    + commandWord + " here!");
        }
        return null;    // all cases except goRoom()
    }
}