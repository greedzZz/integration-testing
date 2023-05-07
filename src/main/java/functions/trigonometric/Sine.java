package functions.trigonometric;

import functions.Function;

public class Sine implements Function {

    public double value(double x, double eps) {
        if (x == Double.NEGATIVE_INFINITY || x == Double.POSITIVE_INFINITY || Double.isNaN(x)) {
            return Double.NaN;
        }
        double fact = 1;
        double res = 0;
        double prev_res = 1;
        double sqrtx, pow;
        int sign = 1;
        int count = 1;
        if (x >= 0) {
            int times = (int) (x / (Math.PI * 2));
            x -= times * Math.PI * 2;
        } else if (x < 0) {
            int times = (int) (Math.abs(x) / (Math.PI * 2));
            x += times * Math.PI * 2;
        }
        sqrtx = x * x;
        pow = x;
        while (Math.abs(prev_res - res) > eps) {
            fact /= count;
            prev_res = res;
            res += sign * pow * fact;
            sign = -sign;
            fact /= (count + 1);
            pow *= sqrtx;
            count += 2;
        }
        if (Math.abs(res) > 1) return Double.NaN;
        if (Math.abs(res) < eps) return 0;
        return res;
    }
}
