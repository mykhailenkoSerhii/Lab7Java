package task2;

import javax.swing.*;
import java.util.List;

public abstract class BaseAI extends Thread
{
    protected final List<Engine> engines;
    protected final JPanel canvas;
    private volatile boolean paused = false;

    public BaseAI(List<Engine> engines, JPanel canvas)
    {
        this.engines = engines;
        this.canvas = canvas;
        setDaemon(true);
    }

    public synchronized void pauseThread()
    {
        paused = true;
    }

    public synchronized void resumeThread()
    {
        paused = false;
        notifyAll();
    }

    protected synchronized void checkPaused() throws InterruptedException
    {
        while(paused) wait();
    }

    protected abstract void step();

    @Override
    public void run()
    {
        try
        {
            while(!isInterrupted())
            {
                checkPaused();
                step();
                SwingUtilities.invokeLater(canvas::repaint);
                Thread.sleep(20);
            }
        } catch(InterruptedException ignored) {}
    }
}