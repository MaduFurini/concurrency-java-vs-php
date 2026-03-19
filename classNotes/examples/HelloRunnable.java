package classNotes.examples;

public class HelloRunnable {
    public static void main(String[] args) {
        (new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Starting thread...");
            }
        })).start();
    }
}
