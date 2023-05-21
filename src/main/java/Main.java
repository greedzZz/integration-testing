import functions.FunctionSystem;
import functions.logarithmic.Ln;
import functions.logarithmic.Logarithm;
import functions.trigonometric.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Main {
    public static void main(String[] args) {
        try {
            FunctionSystem sys = new FunctionSystem();
            Writer w = new FileWriter("src/main/resources/output/system.csv");
            sys.printCSV(w, -5.6, 4, 0.1, 0.00001);

            Cosine cos = new Cosine();
            w = new FileWriter("src/main/resources/output/cos.csv");
            cos.printCSV(w, -Math.PI * 2, Math.PI * 2, 0.1, 0.00001);

            Sine sin = new Sine();
            w = new FileWriter("src/main/resources/output/sin.csv");
            sin.printCSV(w, -Math.PI * 2, Math.PI * 2, 0.1, 0.00001);

            Tangent tan = new Tangent();
            w = new FileWriter("src/main/resources/output/tan.csv");
            tan.printCSV(w, -Math.PI + 0.2, Math.PI - 0.2, 0.1, 0.00001);

            Cotangent cot = new Cotangent();
            w = new FileWriter("src/main/resources/output/cot.csv");
            cot.printCSV(w, -Math.PI + 0.2, Math.PI - 0.2, 0.1, 0.00001);

            Cosecant csc = new Cosecant();
            w = new FileWriter("src/main/resources/output/csc.csv");
            csc.printCSV(w, -Math.PI + 0.2, Math.PI - 0.2, 0.1, 0.00001);

            Secant sec = new Secant();
            w = new FileWriter("src/main/resources/output/sec.csv");
            sec.printCSV(w, -Math.PI + 0.1, Math.PI - 0.1, 0.1, 0.00001);

            Ln ln = new Ln();
            w = new FileWriter("src/main/resources/output/ln.csv");
            ln.printCSV(w, 0.1, 5, 0.1, 0.00001);

            Logarithm log2 = new Logarithm();
            w = new FileWriter("src/main/resources/output/log2.csv");
            log2.printCSVModified(w, 0.1, 5, 0.1, 2, 0.00001);

            Logarithm log5 = new Logarithm();
            w = new FileWriter("src/main/resources/output/log5.csv");
            log5.printCSVModified(w, 0.1, 5, 0.1, 5, 0.00001);

            Logarithm log10 = new Logarithm();
            w = new FileWriter("src/main/resources/output/log10.csv");
            log10.printCSVModified(w, 0.1, 5, 0.1, 10, 0.00001);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}