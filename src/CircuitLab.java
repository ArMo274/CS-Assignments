public class Starship {
    private static int totships = 0;
    private static int actships = 0;
    private static double totfirepow = 0;

    private String id;
    private String name;
    private double shields;
    private double firepow;
    private boolean active;

    public Starship(){
        this("Patrol Frigate", 200, 100);
    }

    public Starship(String name, double shields, double firepow){
        totships++;
        id = "SHIP-" + totships;
        this.name = name;
        this.shields = shields;
        this.firepow = firepow;
        active = true;

        actships++;
        totfirepow += firepow;
    }

    public String getId(){
        return id;
    }
    public String getName() {
        return name;
    }

    public double getShields(){
        return shields;
    }
    public double getFirepow(){
        return firepow;
    }
    public boolean isActive() {
        return active;
    }

    public void setName(String name){
        if(name == null || name.trim().isEmpty()){
            System.out.println("Error: name can't be empty");
            return;
        }
        this.name = name;
    }

    public void setShields(double shields) {
        if(shields < 0){
            System.out.println("Error: shields can't be negative");
            return;
        }
        this.shields = shields;
    }

    public void setFirepow(double firepow){
        if(firepow <= 0) {
            System.out.println("Error: firepower must be greater than 0");
            return;
        }
        if(active){
            totfirepow = totfirepow - this.firepow + firepow;
        }
        this.firepow = firepow;
    }

    public void upgrade(double amt){
        if(!active){
            System.out.println(id + " is not active, can't upgrade");
            return;
        }
        setFirepow(firepow + amt);
    }

    public void takeDamage(double dmg){
        shields -= dmg;
        if(shields < 0){
            shields = 0;
        }
    }

    public void deactivate(){
        if(!active) {
            System.out.println(id + " is already inactive");
            return;
        }
        active = false;
        actships--;
        totfirepow -= firepow;
        System.out.println(id + " has been deactivated");
    }

    public String toString(){
        return id + " | " + name + " | Shields: " + shields
                + " | Firepower: " + firepow + " | Active: " + active;
    }

    public static void printFleet(){
        System.out.println("Total ships: " + totships + " | Active: "
                + actships + " | Total firepower: " + totfirepow);
    }

    public static void main(String[] args){
        System.out.println("--- Test 1: Creating ships ---");
        Starship s1 = new Starship();
        Starship s2 = new Starship("Titan", 800, 450);
        Starship s3 = new Starship("Phantom", 300, 275);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        printFleet();

        System.out.println("\n--- Test 2: Invalid changes ---");
        s2.setFirepow(-50);
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
