package functions;

import functions.logarithmic.Ln;
import functions.logarithmic.Logarithm;
import functions.trigonometric.*;

public class FunctionSystem implements Function {

    private final Sine sin;
    private final Cosine cos;
    private final Tangent tan;
    private final Cotangent cot;
    private final Secant sec;
    private final Cosecant csc;
    private final Ln ln;
    private final Logarithm log;

    public FunctionSystem() {
        this.sin = new Sine();
        this.cos = new Cosine(sin);
        this.tan = new Tangent(sin, cos);
        this.cot = new Cotangent(sin, cos);
        this.sec = new Secant(cos);
        this.csc = new Cosecant(sin);
        this.ln = new Ln();
        this.log = new Logarithm(ln);
    }

    public FunctionSystem(Sine sine, Cosine cosine,
                          Secant secant, Cosecant cosecant,
                          Tangent tangent, Cotangent cotangent,
                          Ln ln, Logarithm logarithm) {
        this.sin = sine;
        this.cos = cosine;
        this.tan = tangent;
        this.cot = cotangent;
        this.sec = secant;
        this.csc = cosecant;
        this.ln = ln;
        this.log = logarithm;
    }

    public double value(double x, double eps) {
        double res;
        if (x <= 0) {
            res = (((((((((Math.pow((((Math.pow(((Math.pow(((cos.value(x, eps) / tan.value(x, eps)) / cos.value(x, eps)), 2)
                    - sin.value(x, eps)) + tan.value(x, eps)), 3) - tan.value(x, eps)) - sin.value(x, eps))
                    / Math.pow(tan.value(x, eps), 2)), 3) + csc.value(x, eps)) + sec.value(x, eps)) / sin.value(x, eps))
                    * sec.value(x, eps)) + csc.value(x, eps)) + (((Math.pow(Math.pow(sec.value(x, eps), 2), 3)
                    + (sec.value(x, eps) / sin.value(x, eps))) + (cos.value(x, eps) * sec.value(x, eps)))
                    * (csc.value(x, eps) * tan.value(x, eps)))) + csc.value(x, eps)) - (cot.value(x, eps)
                    - ((cos.value(x, eps) * ((((sin.value(x, eps) / (tan.value(x, eps) * ((sec.value(x, eps)
                    - (Math.pow(cot.value(x, eps), 3) * tan.value(x, eps))) * sin.value(x, eps))))
                    - sin.value(x, eps)) + (cot.value(x, eps) - ((csc.value(x, eps) + Math.pow(sin.value(x, eps), 2))
                    * sin.value(x, eps)))) + Math.pow((sin.value(x, eps) / (tan.value(x, eps) + cot.value(x, eps))), 2)))
                    + sec.value(x, eps)))) * ((sin.value(x, eps) * (sin.value(x, eps) * ((csc.value(x, eps) + sec.value(x, eps))
                    / cos.value(x, eps)))) - (sin.value(x, eps) + ((((((csc.value(x, eps) * Math.pow(csc.value(x, eps), 2))
                    / Math.pow((csc.value(x, eps) + (((sin.value(x, eps) * csc.value(x, eps)) * (sin.value(x, eps)
                    - (tan.value(x, eps) - sec.value(x, eps)))) * csc.value(x, eps))), 3)) / (cos.value(x, eps)
                    + (cot.value(x, eps) - (csc.value(x, eps) * tan.value(x, eps))))) + cot.value(x, eps))
                    - Math.pow(csc.value(x, eps), 2)) - ((cot.value(x, eps) * csc.value(x, eps)) + sin.value(x, eps))))));
        } else {
            res = (Math.pow(Math.pow(Math.pow((ln.value(x, eps) * ln.value(x, eps)), 2), 2), 3)
                    + (((log.valueModified(x, 5, eps) / ln.value(x, eps))
                    * (log.valueModified(x, 10, eps) * log.valueModified(x, 2, eps))) * ln.value(x, eps)));
        }
        if (res == Double.NEGATIVE_INFINITY || res == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        }
        return res;
    }
}
