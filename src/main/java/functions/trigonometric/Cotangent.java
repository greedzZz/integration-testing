package functions.trigonometric;

import functions.Function;

public class Cotangent implements Function {

    private final Sine sin;
    private final Cosine cos;

    public Cotangent() {
        sin = new Sine();
        cos = new Cosine(sin);
    }

    public Cotangent(Sine sine, Cosine cosine) {
        sin = sine;
        cos = cosine;
    }

    public double value(double x, double eps) {
        if (x == Double.NEGATIVE_INFINITY || x == Double.POSITIVE_INFINITY || Double.isNaN(x)) {
            return Double.NaN;
        }
        double res = cos.value(x, eps) / sin.value(x, eps);
        if (res == Double.NEGATIVE_INFINITY || res == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        }
        return res;
    }
}
