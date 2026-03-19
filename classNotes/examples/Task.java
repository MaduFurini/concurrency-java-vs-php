package classNotes.examples;

public class Task implements Runnable {
    @Override
    public void run() {
        System.out.println("Running thread" + 
            Thread.currentThread().getName()
        );
    }
}
