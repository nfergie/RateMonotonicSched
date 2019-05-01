import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class Scheduler extends Thread {
    Semaphore timSem;
    Semaphore [] semArray;
    FinishData data;

    public Scheduler(Semaphore timSem,Semaphore[] semArray, FinishData data){
        this.semArray = semArray;
        this.data = data;
        this.timSem = timSem;
    }

    @Override
    public void run() {
        try{
            while(true){
                timSem.acquire();
                int count = data.timerCount;
//                System.out.println("count" + data.timerCount);
                if(count == 160){
                    System.out.println("T1: " + data.count1);
                    System.out.println("T2: " + data.count2);
                    System.out.println("T3: " + data.count3);
                    System.out.println("T4: " + data.count4);
                    System.exit(0);
                }
                if(count % 1 == 0){
                    semArray[0].release();
                    data.count1++;
                }
                if(count % 2 == 0){
                    semArray[1].release();
                    data.count2++;
                }
                if(count % 4 == 0){
                    semArray[2].release();
                    data.count3++;
                }
                if(count % 16 == 0){
                    semArray[3].release();
                    data.count4++;
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
