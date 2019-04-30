import java.util.concurrent.Semaphore;
import java.util.Timer;

public class RateMonotonicSched {
    public static void main (String [] args){
        FinishData data = new FinishData();

        Semaphore [] semArray = new Semaphore[5];
        for(int i =0; i < 4; i++){
            semArray[i] = new Semaphore(1);
        }

        Semaphore fin = new Semaphore(1);

        WorkThread t1 = new WorkThread(semArray[0], fin, data, 1, 1);
        WorkThread t2 = new WorkThread(semArray[1], fin, data, 2, 2);
        WorkThread t3 = new WorkThread(semArray[2], fin, data, 4, 3);
        WorkThread t4 = new WorkThread(semArray[3], fin, data, 16, 4);



        Timer sched = new Timer();
        Scheduler s = new Scheduler(semArray, data);
        sched.schedule(s, 0, 10*1000);

        t1.setPriority(4);
        t2.setPriority(3);
        t3.setPriority(2);
        t4.setPriority(1);
    }
}
