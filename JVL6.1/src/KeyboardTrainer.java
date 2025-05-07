import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class KeyboardTrainer extends JPanel implements ActionListener {
    private final int MAX_CIRCLES = 4;
    private final List<FallingCircle> circles = new CopyOnWriteArrayList<>();
    private final Random random = new Random();
    private final javax.swing.Timer timer = new javax.swing.Timer(5, this);
    private final JButton startButton;
    private int score = 0;
    private boolean gameRunning = false;

    public KeyboardTrainer() {
        setPreferredSize(new Dimension(800, 600));
        setLayout(new GridBagLayout());
        setFocusable(true);

        startButton = new JButton("Старт");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.addActionListener(e -> startGame());

        GridBagConstraints gbc = new GridBagConstraints();
        add(startButton, gbc);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (gameRunning) {
                    char pressedKey = e.getKeyChar();
                    for (FallingCircle circle : circles) {
                        if (circle.getLetter() == pressedKey) {
                            score++;
                            circle.stopFalling();
                            circles.remove(circle); // Теперь это безопасно
                            addNewCircle(); // Добавляем новый круг
                        }
                    }
                    checkGameOver();
                }
            }
        });
    }

    private void startGame() {
        gameRunning = true;
        startButton.setVisible(false);
        score = 0;
        circles.clear();
        timer.start();
        requestFocusInWindow();

        // Запускаем начальные 4 круга
        for (int i = 0; i < MAX_CIRCLES; i++) {
            addNewCircle();
        }
    }

    private void addNewCircle() {
        FallingCircle circle = new FallingCircle(this);
        synchronized (circles) {
            circles.add(circle);
        }
        new Thread(circle).start(); // Запускаем поток с шариком
    }

    public void removeCircle(FallingCircle circle) {
        circles.remove(circle);
        addNewCircle();
    }

    public void decreaseScore() {
        if (score > 0) { // Чтобы не уходило в минус
            score--;
        }
    }

    private void checkGameOver() {
        if (score >= 50) {
            gameRunning = false;
            timer.stop();
            synchronized (circles) {
                for (FallingCircle circle : circles) {
                    circle.stopFalling();
                }
            }
            circles.clear();
            JOptionPane.showMessageDialog(this, "Поздравляем! Вы набрали 50 очков!");
            startButton.setVisible(true);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameRunning) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            synchronized (circles) {
                for (FallingCircle circle : circles) {
                    g2d.setColor(Color.BLUE);
                    g2d.fillOval(circle.getX(), circle.getY(), 50, 50);

                    g2d.setColor(Color.WHITE);
                    g2d.setFont(new Font("Arial", Font.BOLD, 20));
                    g2d.drawString(String.valueOf(circle.getLetter()), circle.getX() + 18, circle.getY() + 32);
                }
            }

            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 24));
            g2d.drawString("Счет: " + score, 20, 40);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Тренажер клавиатуры");
        KeyboardTrainer trainer = new KeyboardTrainer();
        frame.add(trainer);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
