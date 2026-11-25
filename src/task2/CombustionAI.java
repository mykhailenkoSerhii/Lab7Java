package task2;

import util.Utils;

import javax.swing.*;
import java.util.List;

public class CombustionAI extends BaseAI
{
    public CombustionAI(List<Engine> engines, JPanel canvas)
    {
        super(engines, canvas);
    }

    @Override
    protected void step()
    {
        for(Engine e : engines)
        {
            if(e instanceof CombustionEngine ce)
            {
                double tx = ce.toCorner ? 0 : ce.startX;
                double ty = ce.toCorner ? 0 : ce.startY;
                double dx = tx - ce.x;
                double dy = ty - ce.y;
                double dist = Math.hypot(dx, dy);

                if(dist <= Utils.V)
                {
                    ce.x = tx;
                    ce.y = ty;
                    ce.toCorner = !ce.toCorner;
                } else
                {
                    ce.x += (dx / dist) * Utils.V;
                    ce.y += (dy / dist) * Utils.V;
                }
            }
        }
    }
}
