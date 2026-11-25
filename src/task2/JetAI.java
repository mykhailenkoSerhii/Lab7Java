package task2;

import util.Utils;

import javax.swing.*;
import java.util.List;
import java.util.Random;

public class JetAI extends BaseAI
{
    private final Random random = new Random();
    private long lastAngleChange = System.currentTimeMillis();

    public JetAI(List<Engine> engines, JPanel canvas)
    {
        super(engines, canvas);
    }

    @Override
    protected void step()
    {
        boolean changeDir = (System.currentTimeMillis() - lastAngleChange) >= Utils.N * 1000L;
        if(changeDir) lastAngleChange = System.currentTimeMillis();

        for(Engine e : engines)
        {
            if(e instanceof JetEngine je)
            {
                if(changeDir) je.angle = random.nextDouble() * 2 * Math.PI;

                je.x += Math.cos(je.angle) * Utils.V;
                je.y += Math.sin(je.angle) * Utils.V;

                if(je.x < Utils.R || je.x > Utils.WIDTH - Utils.R)
                {
                    je.angle = Math.PI - je.angle;
                    je.x = Math.max(Utils.R, Math.min(Utils.WIDTH - Utils.R, je.x));
                }

                if(je.y < Utils.R || je.y > Utils.HEIGHT - Utils.R)
                {
                    je.angle = -je.angle;
                    je.y = Math.max(Utils.R, Math.min(Utils.HEIGHT - Utils.R, je.y));
                }
            }
        }
    }
}
