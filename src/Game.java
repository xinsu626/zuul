import java.util.ArrayList;


public class Game {
    private Parser parser;
    private Room currentRoom;
    private ArrayList<Item> inventory;  //背包

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    private void printInventory() {
        if (inventory.size() == 0) {
            System.out.println("you are not carrying anything");
        } else {
            System.out.print("You have the following:");
            for (int n = 0; n < inventory.size(); n++) {
                Item item = (Item) inventory.get(n);
                System.out.print(" " + item.getDesc());
            }
            System.out.println();
            System.out.println("Total weight: " + Item.totalWeight(inventory));
        }
    }

    public Game()     {
        inventory = new ArrayList<Item>();
        currentRoom = RoomMaker.createRooms(inventory);
        parser = new Parser();
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private boolean processCommand(Command command) {

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();

        if (commandWord.equals("quit")) {       // serpent
            return true;
        }

        if (commandWord.equals("help")) {
            printHelp();
        } else if (commandWord.equals("inventory")) {
            printInventory();
        } else if (commandWord.equals("look")) {
            System.out.println(currentRoom.getLongDescription());
        } else {
            Room r = currentRoom.respond(command, inventory);
            if (r != null) currentRoom = r;
        }

        return false;      // always false
    }

    public void play() {
        printWelcome();
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    public static void main(String[] argv) {
        Game g = new Game();
        g.play();
    }
}

