import java.util.ArrayList;

/**
 * map
 *
 *                                          Dusty Room
 *                                             |
 *                                             |
 *                        zoolab              preproom-------equipmentroom-------fireroom
 *                         |                   |     \             \
 *                         |                   |      \             \
 *       pub------------outside -------------theatre   \             \
 *           \             |                            \            dragonroom
 *            \            |                             basement3
 *             \           |                                       \
 *              \         lab--------------office                   \
 *               \       /                                           \
 *            steamtunnel -- basement1--basement2--basementlab        \
 *                                                       \             \
 *                                                        \             \
 *                                                      valveroom        \
 *                                                          \          rocktunnel
 *                                                           \       ___/   |
 *                                                        bottomroom/       |
 *                                                                          |
 *                                                                       darkroom
 *                                                                          |
 *                                                                       gemroom
 */

public class RoomMaker
{
    //constructor
    public RoomMaker()  {
    }

    private static void link(Room r1, String dir1, Room r2, String dir2) {
        r1.setExit(dir1, r2);
        r2.setExit(dir2, r1);
    }


    public static Room createRooms(ArrayList<Item> inventory)
    {
        Room outside;
        Room pub;
        Room lab;
        Room office;
        Room preproom = null;
        Room theatre;
        Room steamtunnel;
        Room basementlab = null;
        Room valveroom = null;
        Room bottomroom = null;
        Room rocktunnel = null;
        Room basement3 = null;

        outside = new Room("outside the main entrance of the university");
        outside.addItem(new Item("key"));       // cheat
        theatre = new LockedRoom("in a lecture theatre", "north");
        theatre.addItem(new Item("pencil"));
        pub = new Room("in the campus pub");
        pub.addItem(new Item("water", 3));
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        office.addItem(new Item("memo", 1));
        office.addItem(new Light("flashlight"));
        steamtunnel = new SerpentRoom("in a dark steam tunnel beneath campus"
                + "\nThe door just snapped shut above you"
                + "\nA giant serpent is coiled before you!!!");

        Room basement1 = new Room("in a dark basement somewhere");
        Room basement2 = new Room("in a basement somewhere. Dim light and a strange hum"
                + "\ncomes from the north");
        basementlab = new Room("in a huge underground chamber that contains enormous apparatus"
                + "\nfrom a long-ago experiment. At one end of the room"
                + "\nis a pool of water that, in the dim light, looks very deep."
                +"\nThere is also a damp metal stairway leading down");
        basementlab.addItem(new Item("glowing_donut"));
        valveroom = new ValveRoom(true, "down",
                "in a deeper part of the basement, below the experiment chamber."
                        +   "\nThere are many pipes here, and a huge valve.",
                "You find that the steps are flooded with water"
                        +"\nYou need to find a way to drain the water"
        );
        bottomroom = new Room("you are deep in the sub-basement"
                +"\nThe bare-rock walls are dripping with water."
                +"\nA tunnel angles south and slightly upwards");

        rocktunnel = new ValveRoom(false, "north",
                "in a long sloping rock tunnel, very wet at the north end",
                "Hmm...the north end is MUCH too wet to allow progress"
        );

        Room darkroom = new DarkRoom("north",
                "A cavern room with stalactites and stalagmites",
                inventory
        );
        darkroom.addItem(new Item("goldcoin"));              // hmm... will this work?


        Room gemroom = new Room("A small cavern room");
        gemroom.addItem(new Item("bag_of_gems"));

        basement3 = new Room("in a crawlspace under the lecture room"
                + "\nAbove you are the undersides of the seats");

        preproom = new Room("Lecture preparation room"
        );
        preproom.addItem(new Item("tuition_vouchers"));
        preproom.addItem(new Light("torch"));       // pld new

        Room zoolab = new ZooRoom("You are in the zoological laboratory"
                + "\nThere are cages everywhere.",
                inventory
        );

        Room dRoom = new DustyRoom("You are in a very dusty room.\nThe dust is thick and lumpy on the floor.");
        dRoom.addItem(new Item("broom"));


        Room equipmentroom = new Room("in a equipment room" +
                "\nPick a weapon and get ready to fight." +
                "\nNote that there is a dragon in the east room. It is best to have a sword before you enter, otherwise you will have a big problem(locked there forever).");
        equipmentroom.addItem(new Item("sword"));
        equipmentroom.addItem(new Item("slingshot"));
        equipmentroom.addItem(new Item("Bow and arrow"));
        Room dragonroom = new DragonRoom("in a small room" +
                "\nAll the doors are suddenly locked" +
                "\nEat some chocolate, use your sword and get ready to fight");
        dragonroom.addItem(new Edible("chocolate"));

        Room fireroom = new FireRoom("west",
                "in a coffee shop",
                inventory);
        fireroom.addItem(new Item("postcard"));


        link(steamtunnel, "east", basement1, "west");
        link(basement1, "north", basement2, "south");
        link(basement2, "north", basementlab, "south");
        link(basementlab, "down", valveroom, "up");
        link(valveroom, "down", bottomroom, "up");
        link(bottomroom, "south", rocktunnel, "north");
        link(rocktunnel, "south", darkroom, "north");
        link(darkroom, "south", gemroom, "north");
        link(rocktunnel, "up", basement3, "down");
        link(basement3, "up", preproom, "down");
        link(outside, "north", zoolab, "south");
        link (dRoom, "south", preproom, "north");
        link(preproom, "east", equipmentroom, "west");//xin
        link(equipmentroom, "east", fireroom, "west");
        link(equipmentroom, "south", dragonroom, "north");//xin

        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        theatre.setExit("west", outside);
        theatre.setExit("north", preproom);
        pub.setExit("east", outside);
        lab.setExit("north", outside);
        lab.setExit("east", office);
        lab.setExit("down", steamtunnel);
        office.setExit("west", lab);
        steamtunnel.setExit("up", pub);
        preproom.setExit("south", theatre);


        inventory.add(new Light("flashlight", 4));
        inventory.add(new Item("cookie", 2));

        return outside;
    }
}

