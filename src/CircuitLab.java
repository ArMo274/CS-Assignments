public class CircuitLab {
    public static void main(String[] args) {
        LowPass bass = new LowPass("Bass filter", 200, 0.000001);
        BandPass voice = new BandPass("Voice filter", 300, 3400);

        Filter[] filters = {bass, voice};
        for (Filter f : filters) {
            System.out.println(f.getLabel() + " at 100 Hz: " + f.passes(100));
            System.out.println(f.getLabel() + " at 1000 Hz: " + f.passes(1000));
        }
        System.out.printf("Bass filter resistor: %.1f ohms%n", bass.resistorOhms());
        System.out.println("Voice filter bandwidth: " + voice.bandwidthHz() + " Hz");
    }
}

abstract class Filter {
    private final String label;
    protected final double cutoffHz;

    protected Filter(String label, double cutoffHz) {
        if (!(cutoffHz > 0) || !Double.isFinite(cutoffHz))
            throw new IllegalArgumentException("Cutoff must be positive and finite");
        this.label = label;
        this.cutoffHz = cutoffHz;
    }

    public String getLabel() { return label; }

    // Simple passband model. Real filters roll off gradually.
    public abstract boolean passes(double frequencyHz);
}

class LowPass extends Filter {
    private final double capacitanceF;

    public LowPass(String label, double cutoffHz, double capacitanceF) {
        super(label, cutoffHz);
        if (!(capacitanceF > 0) || !Double.isFinite(capacitanceF))
            throw new IllegalArgumentException("Capacitance must be positive and finite");
        this.capacitanceF = capacitanceF;
    }

    public double resistorOhms() {
        return 1 / (2 * Math.PI * cutoffHz * capacitanceF);
    }
    @Override
    public boolean passes(double frequencyHz) {
        return frequencyHz >= 0 && frequencyHz <= cutoffHz;
    }
}

class BandPass extends Filter {
    private final double upperHz;

    public BandPass(String label, double lowerHz, double upperHz) {
        super(label, lowerHz);
        if (!(upperHz > lowerHz) || !Double.isFinite(upperHz))
            throw new IllegalArgumentException("Upper cutoff must exceed lower cutoff and be finite");
        this.upperHz = upperHz;
    }
    public double bandwidthHz() { return upperHz - cutoffHz; }

    @Override
    public boolean passes(double frequencyHz) {
        return frequencyHz >= cutoffHz && frequencyHz <= upperHz;
    }
}

