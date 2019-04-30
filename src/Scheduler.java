import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class Scheduler extends Thread {
    Semaphore timSem;
    Semaphore [] semArray;
    FinishData data;
    int count = 1;

    public Scheduler(Semaphore timSem,Semaphore[] semArray, FinishData data){
        this.semArray = semArray;
        this.data = data;
        this.timSem = timSem;
    }

    @Override
    public void run() {
        try{
            while(count < 17){
                timSem.acquire();
                if(count == 1){
                    System.out.println("Count = " + count);
                    Thread.sleep(1000);
                    semArray[0].release();
                    semArray[1].release();
                    semArray[2].release();
                    semArray[3].release();
                }
                if(count != 1 && count % 1 == 0){
                    System.out.println("Count = " + count);
                    Thread.sleep(1000);
                    semArray[0].release();
                }
                if(count % 2 == 0){
                    System.out.println("Count = " + count);
                    Thread.sleep(1000);
                    semArray[1].release();
                }
                if(count % 4 == 0){
                    System.out.println("Count = " + count);
                    Thread.sleep(1000);
                    semArray[2].release();
                }
                if(count % 16 == 0){
                    System.out.println("Count = " + count);
                    Thread.sleep(1000);
                    semArray[3].release();
                }
                count++;
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
