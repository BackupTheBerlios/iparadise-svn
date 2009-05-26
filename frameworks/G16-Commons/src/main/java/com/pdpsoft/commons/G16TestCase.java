package com.pdpsoft.commons;

import junit.framework.TestCase;
/**
 * Superclass of G16 tests, has some facilities to run concurrent threads
 * Ashna Company, G16
 * User: d_farid
 * Date: Agu 2, 2006
 * Time: 5:04:15 PM
 */
public class G16TestCase extends TestCase {

    protected int c = 0;
    protected Exception exception = null;
    protected int exceptionNumber = -1;

    public G16TestCase(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public static abstract class Executor {
        protected abstract void execute(int id) throws Exception;
    }

    protected void runThreads(int k, final Executor executor) throws Exception {
        c = 0;
        exception = null;
        exceptionNumber = -1;
        for (int counter = 0; counter < k; counter++) {
            final int finalCounter = counter;
            new Thread(new Runnable() {
                public void run() {
                    try {
                        executor.execute(finalCounter);
                    } catch (Exception e) {
                        if (exceptionNumber == -1) {
                            exception = e;
                            exceptionNumber = finalCounter;
                            System.err.println("exceptionNumber = " + exceptionNumber);
                            exception.printStackTrace();
                            exception.getCause().printStackTrace();
                        }
                    } finally {
                        c++;
                    }
                }
            }).start();
        }
        while (c < k) {
            System.err.println(c);
            Thread.sleep(1000);
        }
        if (exception != null) {
            System.err.println("exceptionNumber = " + exceptionNumber);
            exception.printStackTrace();
            exception.getCause().printStackTrace();
        }
    }
}
