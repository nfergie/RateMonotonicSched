import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class MyTimer extends TimerTask {
    Semaphore timSem;

    public MyTimer(Semaphore timSem){
        this.timSem = timSem;
    }

    @Override
    public void run() {
        timSem.release();
    }
}
