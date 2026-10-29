public class Starship {

    private static int totalShips = 0;
    private static int activeShips = 0;
    private static double totalFirepower = 0;

    // per-ship stats
    private String id;
    private String name;
    private double shields;
    private double firepower;
    private boolean active;

    public Starship() {
        this("Patrol Frigate", 200, 100);
    }

    public Starship(String name, double shields, double firepower) {
        totalShips++;
        id = "SHIP-" + totalShips;
        this.name = name;
        this.shields = shields;
        this.firepower = firepower;
        active = true;

        activeShips++;
        totalFirepower += firepower;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getShields() {
        return shields;
    }

    public double getFirepower() {
        return firepower;
    }

    public boolean isActive() {
        return active;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: name can't be empty");
            return;
        }
        this.name = name;
    }

    public void setShields(double shields) {
        if (shields < 0) {
            System.out.println("Error: shields can't be negative");
            return;
        }
        this.shields = shields;
    }

    public void setFirepower(double firepower) {
        if (firepower <= 0) {
            System.out.println("Error: firepower must be greater than 0");
            return;
        }
        if (active) {
            totalFirepower = totalFirepower - this.firepower + firepower;
        }
        this.firepower = firepower;
    }

    public void upgrade(double amount) {
        if (!active) {
            System.out.println(id + " is not active, can't upgrade");
            return;
        }
        setFirepower(firepower + amount);
    }

    public void takeDamage(double damage) {
        shields -= damage;
        if (shields < 0) {
            shields = 0;
        }
    }

    public void deactivate() {
        if (!active) {
            System.out.println(id + " is already inactive");
            return;
        }
        active = false;
        activeShips--;
        totalFirepower -= firepower;
        System.out.println(id + " has been deactivated");
    }

    public String toString() {
        return id + " | " + name + " | Shields: " + shields + " | Firepower: " + firepower + " | Active: " + active;
    }

    public static void printFleet() {
        System.out.println("Total ships: " + totalShips + " | Active: " + activeShips + " | Total firepower: " + totalFirepower);
    }

    public static void main(String[] args) {
        System.out.println("--- Test 1: Creating ships ---");
        Starship s1 = new Starship();
        Starship s2 = new Starship("Titan", 800, 450);
        Starship s3 = new Starship("Phantom", 300, 275);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        printFleet();

        System.out.println("\n--- Test 2: Invalid changes ---");
        s2.setFirepower(-50);
        s3.setName("");
        s1.setShields(-10);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        printFleet();

        System.out.println("\n--- Test 3: Upgrading a ship ---");
        System.out.println("Upgrading Titan by 150");
        s2.upgrade(150);
        System.out.println(s2);
        printFleet();

        System.out.println("\n--- Test 4: Deactivating a ship ---");
        s3.deactivate();
        System.out.println(s3);
        printFleet();

        System.out.println("\n--- Test 5: Deactivating again ---");
        s3.deactivate();
        printFleet();
    }
}