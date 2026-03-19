package classNotes.examples;

public class VolatileExample extends Thread {
    private volatile boolean running = true;

    @Override
    public void run() {
        System.out.println("Secondary thread started");

        while (running) { }

        System.out.println("Secondary thread finished");
    }

    public void pause() {
        this.running = false;
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileExample thread = new VolatileExample();

        thread.start();

        Thread.sleep(2000);

        System.out.println("Requesting stop...");

        thread.pause();
    }
}
