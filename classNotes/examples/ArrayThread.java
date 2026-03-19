package classNotes.examples;

public class ArrayThread {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            final int add = i;

            new Thread(() -> {
                System.out.println(add + 1);
            }).start();
        }
    }
}
