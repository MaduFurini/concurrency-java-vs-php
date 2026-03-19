package classNotes.examples.FrogRace;

public class Race {
    final static int FROGS_COUNT = 5;
    final static int DISTANCE = 500;

    public static void main(String[] args) {
        for (int i = 0; i < FROGS_COUNT; i++) {
            Frog f = new Frog("FROG_" + i, DISTANCE);
            f.start();
        }
    }
}
