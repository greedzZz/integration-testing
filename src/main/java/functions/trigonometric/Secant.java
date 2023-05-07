package functions.trigonometric;

import functions.Function;

public class Secant implements Function {
    private final Cosine cos;

    public Secant() {
        cos = new Cosine();
    }

    public Secant(Cosine cosine) {
        cos = cosine;
    }

    public double value(double x, double eps) {
        double cosRes = cos.value(x, eps);
        if (Double.isNaN(cosRes) || cosRes == 0) return Double.NaN;
        return 1 / cosRes;
    }
}
