package functions.trigonometric;

import functions.Function;

public class Cosecant implements Function {
    private final Sine sin;

    public Cosecant() {
        sin = new Sine();
    }

    public Cosecant(Sine sine) {
        sin = sine;
    }

    public double value(double x, double eps) {
        double sinRes = sin.value(x, eps);
        if (Double.isNaN(sinRes) || sinRes == 0) return Double.NaN;
        return 1 / sinRes;
    }
}
