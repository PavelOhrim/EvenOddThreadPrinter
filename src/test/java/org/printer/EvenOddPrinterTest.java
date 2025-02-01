package org.printer;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EvenOddPrinterTest {

    @Test
    public void testEvenOddPrinting() throws InterruptedException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            EvenOddPrinter printer = new EvenOddPrinter();

            Thread evenThread = new Thread(printer::printEven);
            Thread oddThread = new Thread(printer::printOdd);

            evenThread.start();
            oddThread.start();

            evenThread.join();
            oddThread.join();


            String output = outputStream.toString().trim();


            List<String> lines = Arrays.asList(output.split("\\r?\\n"));


            List<String> expectedOutput = Arrays.asList(
                    "Odd: 1",
                    "Even: 2",
                    "Odd: 3",
                    "Even: 4",
                    "Odd: 5",
                    "Even: 6",
                    "Odd: 7",
                    "Even: 8",
                    "Odd: 9",
                    "Even: 10"
            );


            assertEquals(expectedOutput, lines);

        } finally {

            System.setOut(originalOut);
        }
    }

}