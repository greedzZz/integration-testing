package functions;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public interface Function {

    double value(double x, double eps);

    default void printCSV(Writer out, double from, double to, double step, double eps) {
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            for (double x = from; x <= to; x += step) {
                double res = value(x, eps);
                printer.printRecord(x, res);
            }
        } catch (IOException e) {
            System.out.println("Failed to write CSV record.");
        }
    }
}