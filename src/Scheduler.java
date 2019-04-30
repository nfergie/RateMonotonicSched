import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class Scheduler extends TimerTask {
    Semaphore [] semArray;
    FinishData data;
    int count = 0;

    public Scheduler(Semaphore[] semArray, FinishData data){
        this.semArray = semArray;
        this.data = data;
    }

    @Override
    public void run() {

        System.out.println(count);
        count++;
    }
}
