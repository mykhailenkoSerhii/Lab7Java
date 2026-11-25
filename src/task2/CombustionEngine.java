package task2;

import java.awt.*;

public class CombustionEngine extends Engine
{
    private final double volume;
    public boolean toCorner = true;

    public CombustionEngine(double power, double volume)
    {
        super(power);
        this.volume = volume;
        this.color = Color.BLUE;
        this.label = "Combustion";
    }

    public double getVolume()
    {
        return volume;
    }

    @Override
    public void Show()
    {
        System.out.println("Combustion Engine");
        System.out.printf("Engine Power: %.2f h.p. \nVolume: %.2f liters\n\n", getPower(), getVolume());
    }
}
