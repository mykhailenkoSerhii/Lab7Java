package task1;

import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task1 extends JPanel
{
    private final List<Bee> workers = new ArrayList<>();
    private final List<Bee> drones = new ArrayList<>();

    public Task1()
    {
        setPreferredSize(new Dimension(Utils.WIDTH, Utils.HEIGHT));
        setBackground(Color.WHITE);

        for (int i = 0; i < 3; i++)
        {
            workers.add(new Bee(200 + i * 100, 150 + i * 50, Color.ORANGE));
            drones.add(new Bee(300, 200, Color.RED));
        }

        Thread workerThread = new Thread(
        () -> {
            while (true)
            {
                for (Bee b : workers)
                {
                    double targetX = b.toCorner ? 0 : b.startX;
                    double targetY = b.toCorner ? 0 : b.startY;
                    double dx = targetX - b.x;
                    double dy = targetY - b.y;
                    double dist = Math.hypot(dx, dy);

                    if (dist <= Utils.V)
                    {
                        b.x = targetX;
                        b.y = targetY;
                        b.toCorner = !b.toCorner;
                    }
                    else
                    {
                        b.x += (dx / dist) * Utils.V;
                        b.y += (dy / dist) * Utils.V;
                    }
                }
                repaint();
                sleep(20);
            }
        });

        Thread droneThread = new Thread(
        () -> {
            Random rand = new Random();
            long lastChange = System.currentTimeMillis();

            while (true)
            {
                if (System.currentTimeMillis() - lastChange >= Utils.N * 1000L)
                {
                    for (Bee d : drones)
                        d.angle = rand.nextDouble() * 2 * Math.PI;

                    lastChange = System.currentTimeMillis();
                }

                for (Bee d : drones)
                {
                    d.x += Math.cos(d.angle) * Utils.V;
                    d.y += Math.sin(d.angle) * Utils.V;

                    if (d.x < Utils.R || d.x > Utils.WIDTH - Utils.R) {
                        d.angle = Math.PI - d.angle;
                        d.x = Math.max(Utils.R, Math.min(Utils.WIDTH - Utils.R, d.x));
                    }
                    if (d.y < Utils.R || d.y > Utils.HEIGHT - Utils.R) {
                        d.angle = -d.angle;
                        d.y = Math.max(Utils.R, Math.min(Utils.HEIGHT - Utils.R, d.y));
                    }
                }
                repaint();
                sleep(20);
            }
        });

        workerThread.start();
        droneThread.start();
    }

    private void sleep(int ms)
    {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException ignored) {}
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (Bee b : workers)
        {
            g.setColor(b.color);
            g.fillOval((int) (b.x - Utils.R), (int) (b.y - Utils.R), Utils.R * 2, Utils.R * 2);
        }

        for (Bee d : drones)
        {
            g.setColor(d.color);
            g.fillOval((int) (d.x - Utils.R), (int) (d.y - Utils.R), Utils.R * 2, Utils.R * 2);
        }
    }

    public static void task1()
    {
        SwingUtilities.invokeLater(() ->
            {
                JFrame frame = new JFrame("Lab7 - Task 1");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new Task1());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });
    }
}