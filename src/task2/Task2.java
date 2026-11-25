package task2;

import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Task2 extends JPanel
{
    public Task2()
    {
        setLayout(new BorderLayout());

        List<Engine> engines = new CopyOnWriteArrayList<>();

        CombustionEngine ce1 = new CombustionEngine(150.0, 2.5);
        ce1.setPosition(220, 140);

        CombustionEngine ce2 = new CombustionEngine(220.0, 4.0);
        ce2.setPosition(380, 240);

        JetEngine je1 = new JetEngine(5000.0, 120.0);
        je1.setPosition(280, 200);

        JetEngine je2 = new JetEngine(8500.0, 210.0);
        je2.setPosition(450, 120);

        engines.add(ce1);
        engines.add(ce2);
        engines.add(je1);
        engines.add(je2);

        JPanel canvas = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                for (Engine e : engines)
                {
                    g.setColor(e.color);
                    g.fillOval((int) (e.x - Utils.R), (int) (e.y - Utils.R),
                            Utils.R * 2, Utils.R * 2);
                    g.setColor(Color.BLACK);
                    g.drawString(e.label + " (" + e.getPower() + ")",
                            (int) (e.x - Utils.R), (int) (e.y - Utils.R - 4));
                }
            }
        };
        canvas.setPreferredSize(new Dimension(Utils.WIDTH, Utils.HEIGHT));
        canvas.setBackground(Color.WHITE);
        add(canvas, BorderLayout.CENTER);

        CombustionAI combustionAI = new CombustionAI(engines, canvas);
        JetAI jetAI = new JetAI(engines, canvas);
        combustionAI.start();
        jetAI.start();

        JPanel controls = new JPanel(new GridLayout(2, 1));
        controls.add(makeRow("CombustionEngine (Сині):", combustionAI));
        controls.add(makeRow("JetEngine (Червоні):", jetAI));
        add(controls, BorderLayout.SOUTH);
    }

    private JPanel makeRow(String title, BaseAI ai)
    {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.add(new JLabel(title));

        JButton pauseBtn = new JButton("Пауза");
        JButton resumeBtn = new JButton("Продовжити");
        pauseBtn.addActionListener(e -> ai.pauseThread());
        resumeBtn.addActionListener(e -> ai.resumeThread());

        String[] items = {"MIN_PRIORITY", "NORM_PRIORITY", "MAX_PRIORITY"};
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setSelectedIndex(1);
        combo.addActionListener(e -> {
            int i = combo.getSelectedIndex();
            if (i == 0) ai.setPriority(Thread.MIN_PRIORITY);
            else if (i == 1) ai.setPriority(Thread.NORM_PRIORITY);
            else ai.setPriority(Thread.MAX_PRIORITY);
        });

        row.add(pauseBtn);
        row.add(resumeBtn);
        row.add(new JLabel("Пріоритет:"));
        row.add(combo);
        return row;
    }

    public static void task2()
    {
        SwingUtilities.invokeLater(() ->
            {
                JFrame frame = new JFrame("Lab7 - Task 2");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new Task2());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });
    }
}