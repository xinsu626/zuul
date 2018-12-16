import java.util.ArrayList;

public class Extinguisher extends Item
{
    private boolean is_On = false;

    public Extinguisher(String desc, int weight) {

        super(desc, weight);
    }

    public Extinguisher(String desc) {

        super(desc);
    }

    public boolean isOn () {

        return is_On;
    }

    public void open() {

        is_On = true;
    }

    public static Extinguisher getExtinguisher(ArrayList<Item> inv) {
        for (Item itm : inv) {
            if (itm instanceof Extinguisher) {
                Extinguisher et = (Extinguisher) itm;
                if (et.isOn()){
                    return et;
                }
            }
        }
        return null;
    }

    public static boolean hasExtinguisher(ArrayList<Item> inv) {
        return (getExtinguisher(inv) != null) ;
    }
}
