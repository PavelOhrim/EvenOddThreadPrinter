package org.printer;

public class MainApp {

    public static void main(String[] args) {
        EvenOddPrinter printer = new EvenOddPrinter();

        Thread evenThread = new Thread(printer::printEven);
        Thread oddThread = new Thread(printer::printOdd);

        evenThread.start();
        oddThread.start();
    }
}
