import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class MyTimer extends TimerTask {
    private Semaphore timSem;

    MyTimer(Semaphore timSem){
        this.timSem = timSem;
    }

    @Override
    public void run() {
        FinishData.timerCount++;
        timSem.release();
    }
}
