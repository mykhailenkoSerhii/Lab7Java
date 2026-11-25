package task1;

import java.awt.*;

public class Bee
{
    public double x, y, startX, startY, angle;
    public boolean toCorner = true;
    public Color color;

    public Bee(double x, double y, Color color)
    {
        this.x = this.startX = x;
        this.y = this.startY = y;
        this.color = color;
        this.angle = Math.random() * 2 * Math.PI;
    }
}
