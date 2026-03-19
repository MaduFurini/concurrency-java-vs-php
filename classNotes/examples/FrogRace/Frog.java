package classNotes.examples.FrogRace;

public class Frog extends Thread {
    final static int MAX_JUMP = 50;

    private String name;
    private int distanceTraveled;
    private int totalRaceDistance;
    private int jumpDistance;
    private int jumpCount;
    private static int placement;

    public Frog(String name, int totalDistance) {
        super(name);

        this.name = name;
        this.totalRaceDistance = totalDistance;
    }

    public void run() {
        while (distanceTraveled < totalRaceDistance) {
            this.jump();
            this.printCondition();
            this.rest();
        }

        frogPlacement();
    }

    private void rest() {
        Thread.yield();
    }

    private void printCondition() {
        System.out.println("The " + this.name + " jumped " + this.jumpDistance +
            "cm \t and already covered " + this.distanceTraveled + "cm");
    }

    private void jump() {
        this.jumpCount++;
        this.jumpDistance = (int)(Math.random() * MAX_JUMP);
        this.distanceTraveled += this.jumpDistance;

        if (this.distanceTraveled > this.totalRaceDistance) {
            this.distanceTraveled = this.totalRaceDistance;
        }
    }

    public void frogPlacement() {
        placement++;

        System.out.println(name + " was the " + placement
            + "placed, with " + jumpCount + " jumps");
    }
}
