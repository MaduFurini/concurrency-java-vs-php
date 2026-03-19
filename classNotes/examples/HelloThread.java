package classNotes.examples;

public class HelloThread extends Thread {
    public void run() {
        System.out.println("Starting with thread class");
    }

    public static void main(String[] args) {
        (new HelloThread()).start();
    }
}
