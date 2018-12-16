import java.util.ArrayList;

public class Light extends Item {
    private boolean is_On = false;

    public Light(String desc, int weight) {

        super(desc, weight);
    }

    public Light(String desc) {

        super(desc);
    }

    public boolean isOn () {

        return is_On;
    }

    public void light() {
        is_On = true;
    }

    public static Light getLight(ArrayList<Item> inv) {
        for (Item itm : inv) {
            if (itm instanceof Light) {
                Light lt = (Light) itm;
                if (lt.isOn()) return lt;
            }
        }
        return null;
    }

    public static boolean haslight(ArrayList<Item> inv) {
        return (getLight(inv) != null) ;
    }
}
