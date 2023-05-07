package functions.logarithmic;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

import functions.Function;

public class Logarithm implements Function {

    private final Ln ln;

    public Logarithm() {
        this.ln = new Ln();
    }

    public Logarithm(Ln ln) {
        this.ln = ln;
    }

    public double value(double x, double eps) {
        return ln.value(x, eps);
    }

    public double valueModified(double x, double a, double eps) {
        return value(x, eps) / value(a, eps);
    }

    public void printCSVModified(Writer out, double from, double to, double step, double a, double eps) {
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            for (double x = from; x <= to; x += step) {
                double res = valueModified(x, a, eps);
                printer.printRecord(x, res);
            }
        } catch (IOException e) {
            System.out.println("Failed to write CSV record.");
        }
    }
}
