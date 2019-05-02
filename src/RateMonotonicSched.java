import java.util.concurrent.Semaphore;
import java.util.Timer;

public class RateMonotonicSched {
    public static void main (String [] args){
        FinishData data = new FinishData();

        Semaphore [] semArray = new Semaphore[5];
        for(int i =0; i < 4; i++){
            semArray[i] = new Semaphore(1);
            try{
                semArray[i].acquire();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }


        Semaphore fin = new Semaphore(1);
        Semaphore timSem = new Semaphore(1);
        try{
            timSem.acquire();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        WorkThread t1 = new WorkThread(semArray[0], fin, data, 2000, 1);
        WorkThread t2 = new WorkThread(semArray[1], fin, data, 2, 2);
        WorkThread t3 = new WorkThread(semArray[2], fin, data, 4, 3);
        WorkThread t4 = new WorkThread(semArray[3], fin, data, 16, 4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.setPriority(4);
        t2.setPriority(3);
        t3.setPriority(2);
        t4.setPriority(1);

        t1.setName("T1");
        t2.setName("T2");
        t3.setName("T3");
        t4.setName("T4");


        Timer sched = new Timer();
        Scheduler s = new Scheduler(timSem, fin, semArray, data);
        s.start();
        MyTimer t = new MyTimer(timSem, data);
        sched.schedule(t, 0, 1);

    }
}
