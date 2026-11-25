package task2;

import java.awt.*;

public class Engine
{
    private final double power;
    public double x, y;
    protected double startX, startY, angle;
    public Color color = Color.GRAY;
    public String label = "Engine";

    public Engine(double power)
    {
        this.power = power;
    }

    public double getPower()
    {
        return power;
    }

    public void setPosition(double x, double y)
    {
        this.x = this.startX = x;
        this.y = this.startY = y;
    }

    public void Show()
    {
        System.out.println("Engine");
        System.out.printf("Engine Power: %.2f h.p.\n\n", getPower());
    }
}
