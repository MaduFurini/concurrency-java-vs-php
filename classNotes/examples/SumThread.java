package classNotes.examples;

public class SumThread {
    public static void main(String[] args) {
        new Thread(() -> {
            int amount = 2000;

            for (int i = 0; i < 100; i++) {
                amount += i;
            }

            System.out.println("Amount 1: " + amount);
        }).start();

        new Thread(() -> {
            int amount = 4000;

            for (int i = 0; i < 200; i++) {
                amount += i;
            }

            System.out.println("Amount 2: " + amount);
        }).start();
    }
}
