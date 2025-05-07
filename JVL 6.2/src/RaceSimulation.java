import java.util.Random;

public class RaceSimulation {

    private static final int PARTICIPANT_COUNT = 5;
    private static final int GOAL = 10;
    private static boolean winnerDeclared = false;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        for (int i = 1; i <= PARTICIPANT_COUNT; i++) {
            String participantName = "Участник-" + i;
            Thread thread = new Thread(new Counter(participantName), participantName);
            thread.start();
        }
    }

    static class Counter implements Runnable {
        private final String name;
        private final Random random = new Random();

        public Counter(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 1; i <= GOAL; i++) {
                try {
                    Thread.sleep(100 + random.nextInt(200));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " считает: " + i);
            }

            synchronized (lock) {
                if (!winnerDeclared) {
                    winnerDeclared = true;
                    System.out.println("🏁 " + name + " завершил первым и победил!");
                } else {
                    System.out.println(name + " завершил счет.");
                }
            }
        }
    }
}
