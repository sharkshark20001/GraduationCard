package graduation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Card extends JPanel implements ActionListener {

    private Image balloon;
    private Image star;

    private final int NUM_BALLOONS = 7;
    private final int NUM_STARS = 10;

    private int[] balloonX = new int[NUM_BALLOONS];
    private int[] balloonY = new int[NUM_BALLOONS];

    private int[] starX = new int[NUM_STARS];
    private int[] starY = new int[NUM_STARS];

    private Timer timer;
    private Random random = new Random();

    public Card() {
        setPreferredSize(new Dimension(600, 400));
        setBackground(new Color(173, 216, 230)); // добавляем фон

        balloon = new ImageIcon(getClass().getResource("balloon.png")).getImage();
        star = new ImageIcon(getClass().getResource("star.png")).getImage();

        for (int i = 0; i < NUM_BALLOONS; i++) {
            balloonX[i] = random.nextInt(600 - 80);
            balloonY[i] = -random.nextInt(400);
        }

        for (int i = 0; i < NUM_STARS; i++) {
            starX[i] = random.nextInt(600 - 60);
            starY[i] = -random.nextInt(400);
        }

        timer = new Timer(30, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        g.setFont(new Font("Serif", Font.BOLD, 28));
        g.drawString("Поздравляем с выпускным!", 100, 50);

        for (int i = 0; i < NUM_BALLOONS; i++) {
            g.drawImage(balloon, balloonX[i], balloonY[i], 80, 100, this);
        }

        for (int i = 0; i < NUM_STARS; i++) {
            g.drawImage(star, starX[i], starY[i], 60, 60, this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < NUM_BALLOONS; i++) {
            balloonY[i] += 2;
            if (balloonY[i] > getHeight()) {
                balloonY[i] = -100;
                balloonX[i] = random.nextInt(getWidth() - 80);
            }
        }

        for (int i = 0; i < NUM_STARS; i++) {
            starY[i] += 3;
            if (starY[i] > getHeight()) {
                starY[i] = -60;
                starX[i] = random.nextInt(getWidth() - 60);
            }
        }

        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Выпускной");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Card());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
