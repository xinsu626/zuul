import java.util.ArrayList;

public class ValveRoom extends Room
{
    // instance variables - replace the example below with your own
    private static boolean valve_open = false;
    private boolean hasvalve;
    private String wetdirection, wetproblem;

    public ValveRoom(boolean hasvalve, String wetdirection,
                     String description, String wetproblem) {
        super(description);
        this.hasvalve = hasvalve;
        this.wetdirection = wetdirection;
        this.wetproblem = wetproblem;
        valve_open = false;
    }

    public Room respond(Command c, ArrayList<Item> inv) {
        String commandWord = c.getCommandWord();
        String object = c.getSecondWord();
        if (commandWord.equals("go")
                && object.equals(wetdirection)
                && !valve_open)
        {
            System.out.println(wetproblem);
            return null;
        }
        if (hasvalve && commandWord.equals("turn")) {
            if (object.equals("valve")) {
                valve_open = true;
                System.out.println("There is an enormous sound of water in the pipes below");
                return null;
            }else {
                System.out.println("You can't turn that!");
                return null;
            }
        }
        return super.respond(c, inv);
    }

    private boolean valveopen() {
        return valve_open;
    }

}
