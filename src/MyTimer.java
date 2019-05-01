import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class MyTimer extends TimerTask {
    Semaphore timSem;
    FinishData data;

    public MyTimer(Semaphore timSem, FinishData data){
        this.data = data;
        this.timSem = timSem;
    }

    @Override
    public void run() {
        data.timerCount++;
        timSem.release();
    }
}
