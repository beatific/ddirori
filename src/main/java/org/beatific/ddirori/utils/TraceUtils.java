package org.beatific.ddirori.utils;

import java.io.PrintStream;

public class TraceUtils {

	public static void printStackTrace(StackTraceElement[] trace) {
        printStackTrace(System.err, trace);
    }

    public static void printStackTrace(PrintStream s, StackTraceElement[] trace) {
        printStackTrace(new WrappedPrintStream(s), trace);
    }

    private static void printStackTrace(PrintStreamOrWriter s, StackTraceElement[] trace) {
    	
        synchronized (s.lock()) {
            
            for (StackTraceElement traceElement : trace)
                s.println("\tat " + traceElement);
        }
    }
	
	private static class WrappedPrintStream extends PrintStreamOrWriter {
        private final PrintStream printStream;

        WrappedPrintStream(PrintStream printStream) {
            this.printStream = printStream;
        }

        Object lock() {
            return printStream;
        }

        void println(Object o) {
            printStream.println(o);
        }
    }
	
	private abstract static class PrintStreamOrWriter {
        /** Returns the object to be locked when using this StreamOrWriter */
        abstract Object lock();

        /** Prints the specified string as a line on this StreamOrWriter */
        abstract void println(Object o);
    }
}
