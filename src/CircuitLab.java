abstract class BaseEntity {
    private final String name;

    public BaseEntity(String name) {
        this.name = name;
    }



    public String getName() {
        return name;

    }

    public abstract double resistance();
}
class SeriesPair extends BaseEntity {
    private final double firstOhms;
    private final double secondOhms;

    public SeriesPair(double firstOhms, double secondOhms) {
        super("Series pair");
        this.firstOhms = firstOhms;
        this.secondOhms = secondOhms;
    }

    @Override
    public double resistance() {
        return firstOhms + secondOhms;
    }

    public double resistance(double extraOhms) {
        return resistance() + extraOhms;
    }
}

class ParallelPair extends BaseEntity {

    private final double firstOhms;
    private final double secondOhms;

    public ParallelPair(double firstOhms, double secondOhms) {
        super("Parallel pair");
        this.firstOhms = firstOhms;
        this.secondOhms = secondOhms;
    }

    @Override
    public double resistance() {
        return 1.0 / (1.0 / firstOhms + 1.0 / secondOhms);
    }
}

public class CircuitLab {
    public static void main(String[] args) {

        SeriesPair series = new SeriesPair(100, 300);
        ParallelPair parallel = new ParallelPair(100, 300);
        BaseEntity[] circuits = {series, parallel};

        System.out.println("Overriding with 100-ohm and 300-ohm resistors");
        for (BaseEntity circuit : circuits) {
            System.out.printf("%s = %.1f ohms%n", circuit.getName(), circuit.resistance());


        }

        System.out.println("Overloading with an extra 50-ohm resistor");
        System.out.printf("Series total = %.1f ohms%n", series.resistance(50));

    }
}



