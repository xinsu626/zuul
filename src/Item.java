import java.util.ArrayList;


public class Item
{
    private String description; //Item Name
    private int weight;	//Item weight

    public Item(String d)
    {
        description = d;
        weight = 0;
    }

    public Item(String d, int w) {
        description = d;
        weight = w;
    }

    public String getDesc() {

        return description;
    }


    public int getWeight() {
        return weight;
    }

    public static Item findByName(String s, ArrayList<Item> L) {
        int n=0;
        while (n < L.size()) {
            Item i = L.get(n);
            if (s.equals(i.getDesc()))
                return i;
            n++;
        }
        return null;    // not found above
    }

    public static int totalWeight(ArrayList<Item> L) {
        int n=0;
        int sum = 0;
        while (n < L.size()) {
            Item i =  L.get(n);
            sum += i.getWeight();
            n++;
        }
        return sum;    // not found above
    }
}
