import java.util.ArrayList;

public class Edible extends Item {

    private int eat_times = 0;

    public Edible(String desc, int weight) {
        super(desc, weight);
    }

    public Edible(String desc) {
        super(desc);
    }

    public int getEat_times() {
        return eat_times;
    }

    public void eat() {
        eat_times += 1;
    }

    public static Edible getEdible(ArrayList<Item> inventory) {
        for (Item item: inventory) {
            if (item instanceof Edible) {
                Edible ea = (Edible) item;
                return ea;
            }
        }
        return null;
    }

    public static boolean hasEdible(ArrayList<Item> inventory) {
        return (getEdible(inventory)!= null);
    }
}