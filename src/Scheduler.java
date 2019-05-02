import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class Scheduler extends Thread {
    Semaphore fin;
    Semaphore timSem;
    Semaphore [] semArray;
    FinishData data;

    public Scheduler(Semaphore timSem, Semaphore fin, Semaphore[] semArray, FinishData data){
        this.fin = fin;
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
                if(count == 160){
                    System.out.println("T1: " + data.count1);
                    System.out.println("T2: " + data.count2);
                    System.out.println("T3: " + data.count3);
                    System.out.println("T4: " + data.count4);
                    System.out.println("t1 over " + data.over1);
                    System.out.println("t2 over " + data.over2);
                    System.out.println("t3 over " + data.over3);
                    System.out.println("t4 over " + data.over4);
                    System.exit(0);
                }
                if(semArray[0].availablePermits() == 0){
                    semArray[0].release();
                }
                fin.acquire();
                if(data.isFinish1 == false){
                    data.over1++;
                }
                fin.release();
                if(count % 2 == 0){
                    if(semArray[1].availablePermits() == 0){
                        semArray[1].release();
                    }
                    fin.acquire();
                    if(data.isFinish2 == false){
                        data.over2++;
                    }
                    fin.release();
                }
                if(count % 4 == 0){
                    if(semArray[2].availablePermits() == 0){
                        semArray[2].release();
                    }
                    fin.acquire();
                    if(data.isFinish3 == false){
                        data.over3++;
                    }
                    fin.release();
                }
                if(count % 16 == 0){
                    if(semArray[3].availablePermits() == 0){
                        semArray[3].release();
                    }
                    fin.acquire();
                    if(data.isFinish4 == false){
                        data.over4++;
                    }
                    fin.release();
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
