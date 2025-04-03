import java.util.Random;

public class FallingCircle implements Runnable {
    private final char letter;
    private int x, y;
    private final int speed;
    private final Random random = new Random();
    private volatile boolean falling = true;
    private final KeyboardTrainer trainer;

    public FallingCircle(KeyboardTrainer trainer) {
        this.trainer = trainer;
        letter = (char) ('a' + random.nextInt(26));
        x = random.nextInt(750);
        y = 0;
        speed = 2 + random.nextInt(3);
    }

    @Override
    public void run() {
        while (falling && y < 600) {
            y += speed;
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (falling) {
            trainer.decreaseScore(); // Уменьшаем счет
            trainer.removeCircle(this); // Удаляем текущий круг и создаем новый
        }
    }


    public void stopFalling() {
        falling = false;
    }

    public char getLetter() { return letter; }
    public int getX() { return x; }
    public int getY() { return y; }
}
