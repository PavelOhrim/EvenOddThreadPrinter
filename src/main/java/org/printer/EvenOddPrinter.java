package org.printer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class EvenOddPrinter {
    private final Lock lock = new ReentrantLock();
    private final Condition evenTurn = lock.newCondition();
    private final Condition oddTurn = lock.newCondition();
    private boolean isOddTurn = true;

    public void printEven() {
        for (int i = 2; i <= 10; i += 2) {
            lock.lock();
            try {
                while (isOddTurn) {
                    evenTurn.await();
                }
                System.out.println("Even: " + i);
                isOddTurn = true;
                oddTurn.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printOdd() {
        for (int i = 1; i <= 9; i += 2) {
            lock.lock();
            try {
                while (!isOddTurn) {
                    oddTurn.await();
                }
                System.out.println("Odd: " + i);
                isOddTurn = false;
                evenTurn.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}
