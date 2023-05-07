package functions;

import functions.FunctionSystem;
import functions.logarithmic.Ln;
import functions.logarithmic.Logarithm;
import functions.trigonometric.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

public class FunctionSystemTest {

    static double eps = 0.0001;
    static double testEps = 2;

    static Sine sinMock;
    static Cosine cosMock;
    static Tangent tanMock;
    static Secant secMock;
    static Cosecant cscMock;
    static Cotangent cotMock;
    static Ln lnMock;
    static Logarithm logMock;

    static Reader sinIn;
    static Reader cosIn;
    static Reader tanIn;
    static Reader secIn;
    static Reader cscIn;
    static Reader cotIn;
    static Reader lnIn;
    static Reader log2In;
    static Reader log5In;
    static Reader log10In;

    @BeforeAll
    static void init() {
        cosMock = Mockito.mock(Cosine.class);
        sinMock = Mockito.mock(Sine.class);
        tanMock = Mockito.mock(Tangent.class);
        cotMock = Mockito.mock(Cotangent.class);
        cscMock = Mockito.mock(Cosecant.class);
        secMock = Mockito.mock(Secant.class);
        lnMock = Mockito.mock(Ln.class);
        logMock = Mockito.mock(Logarithm.class);
        try {
            cosIn = new FileReader("src/main/resources/input/cos.csv");
            sinIn = new FileReader("src/main/resources/input/sin.csv");
            tanIn = new FileReader("src/main/resources/input/tan.csv");
            cotIn = new FileReader("src/main/resources/input/cot.csv");
            cscIn = new FileReader("src/main/resources/input/csc.csv");
            secIn = new FileReader("src/main/resources/input/sec.csv");
            lnIn = new FileReader("src/main/resources/input/ln.csv");
            log2In = new FileReader("src/main/resources/input/log2.csv");
            log5In = new FileReader("src/main/resources/input/log5.csv");
            log10In = new FileReader("src/main/resources/input/log10.csv");

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(sinIn);
            for (CSVRecord record : records) {
                Mockito.when(sinMock.value(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(cosIn);
            for (CSVRecord record : records) {
                Mockito.when(cosMock.value(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(tanIn);
            for (CSVRecord record : records) {
                Mockito.when(tanMock.value(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(secIn);
            for (CSVRecord record : records) {
                Mockito.when(secMock.value(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(cscIn);
            for (CSVRecord record : records) {
                Mockito.when(cscMock.value(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(cotIn);
            for (CSVRecord record : records) {
                Mockito.when(cotMock.value(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(lnIn);
            for (CSVRecord record : records) {
                Mockito.when(lnMock.value(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log2In);
            for (CSVRecord record : records) {
                Mockito.when(logMock.valueModified(Double.parseDouble(record.get(0)), 2, eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log5In);
            for (CSVRecord record : records) {
                Mockito.when(logMock.valueModified(Double.parseDouble(record.get(0)), 5, eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log10In);
            for (CSVRecord record : records) {
                Mockito.when(logMock.valueModified(Double.parseDouble(record.get(0)), 10, eps)).thenReturn(Double.valueOf(record.get(1)));
            }
        } catch (IOException ex) {
            System.err.println("Failed to read CSV record.");
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/system.csv")
    void testAllMocks(double value, double expected) {
        FunctionSystem function = new FunctionSystem(sinMock, cosMock, secMock, cscMock, tanMock, cotMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.value(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/system.csv")
    void testSin(double value, double expected) {
        FunctionSystem function = new FunctionSystem(new Sine(), cosMock, secMock, cscMock, tanMock, cotMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.value(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/system.csv")
    void testCosInnerMock(double value, double expected) {
        FunctionSystem function = new FunctionSystem(sinMock, new Cosine(sinMock), secMock, cscMock, tanMock, cotMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.value(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/system.csv")
    void testSinCos(double value, double expected) {
        FunctionSystem function = new FunctionSystem(new Sine(), new Cosine(), secMock, cscMock, tanMock, cotMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.value(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/system.csv")
    void testSecInnerMock(double value, double expected) {
        FunctionSystem function = new FunctionSystem(sinMock, cosMock, new Secant(cosMock), cscMock, tanMock, cotMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.value(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/system.csv")
    void testSinCosSec(double value, double expected) {
        FunctionSystem function = new FunctionSystem(new Sine(), new Cosine(), new Secant(), cscMock, tanMock, cotMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.value(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/system.csv")
    void testCscInnerMock(double value, double expected) {
        FunctionSystem function = new FunctionSystem(sinMock, cosMock, secMock, new Cosecant(sinMock), tanMock, cotMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.value(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/system.csv")
    void testSinCsc(double value, double expected) {
        FunctionSystem function = new FunctionSystem(new Sine(), cosMock, secMock, new Cosecant(), tanMock, cotMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.value(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/system.csv")
    void testTanInnerMock(double value, double expected) {
        FunctionSystem function = new FunctionSystem(sinMock, cosMock, secMock, cscMock, new Tangent(sinMock, cosMock), cotMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.value(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/system.csv")
    void testSinCosTan(double value, double expected) {
        FunctionSystem function = new FunctionSystem(new Sine(), new Cosine(), secMock, cscMock, new Tangent(), cotMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.value(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/system.csv")
    void testCotInnerMock(double value, double expected) {
        FunctionSystem function = new FunctionSystem(sinMock, cosMock, secMock, cscMock, tanMock, new Cotangent(sinMock, cosMock), lnMock, logMock);
        Assertions.assertEquals(expected, function.value(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/system.csv")
    void testSinCosCot(double value, double expected) {
        FunctionSystem function = new FunctionSystem(new Sine(), new Cosine(), secMock, cscMock, tanMock, new Cotangent(), lnMock, logMock);
        Assertions.assertEquals(expected, function.value(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/system.csv")
    void testTrig(double value, double expected) {
        FunctionSystem function = new FunctionSystem(new Sine(), new Cosine(), new Secant(), new Cosecant(), new Tangent(), new Cotangent(), lnMock, logMock);
        Assertions.assertEquals(expected, function.value(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/system.csv")
    void testLn(double value, double expected) {
        FunctionSystem function = new FunctionSystem(sinMock, cosMock, secMock, cscMock, tanMock, cotMock, new Ln(), logMock);
        Assertions.assertEquals(expected, function.value(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/system.csv")
    void testLogInnerMock(double value, double expected) {
        FunctionSystem function = new FunctionSystem(sinMock, cosMock, secMock, cscMock, tanMock, cotMock, lnMock, new Logarithm(lnMock));
        Assertions.assertEquals(expected, function.value(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/system.csv")
    void testLog(double value, double expected) {
        FunctionSystem function = new FunctionSystem(sinMock, cosMock, secMock, cscMock, tanMock, cotMock, new Ln(), new Logarithm());
        Assertions.assertEquals(expected, function.value(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input/system.csv")
    void testAll(double value, double expected) {
        FunctionSystem function = new FunctionSystem(new Sine(), new Cosine(), new Secant(), new Cosecant(), new Tangent(), new Cotangent(), new Ln(), new Logarithm());
        Assertions.assertEquals(expected, function.value(value, eps), testEps);
    }
}
