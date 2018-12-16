
public class CommandWords {

    //all legal command words
    private static final String[] validCommands = {
            "go",
            "quit",
            "help",
            "inventory",
            "take",
            "drop",
            "look",
            "give",
            "light",
            "turn",
            "sweep",
            "open",
            "fight_with",
            "eat"
    };

    public CommandWords() {
    }

    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    public void showAll() {
        for(int i = 0; i < validCommands.length; i++) {
            System.out.print(validCommands[i] + "  ");
        }
        System.out.println();
    }
}
