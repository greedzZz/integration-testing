package functions.trigonometric;

import functions.Function;

public class Tangent implements Function {

    private final Sine sin;
    private final Cosine cos;

    public Tangent() {
        sin = new Sine();
        cos = new Cosine(sin);
    }

    public Tangent(Sine sine, Cosine cosine) {
        sin = sine;
        cos = cosine;
    }

    public double value(double x, double eps) {
        if (x == Double.NEGATIVE_INFINITY || x == Double.POSITIVE_INFINITY || Double.isNaN(x)) {
            return Double.NaN;
        }
        double res = sin.value(x, eps) / cos.value(x, eps);
        if (res == Double.NEGATIVE_INFINITY || res == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        }
        return res;
    }
}
