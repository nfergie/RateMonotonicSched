import java.util.concurrent.Semaphore;

public class Scheduler extends Thread {
    private Semaphore fin;
    private Semaphore timSem;
    private Semaphore [] semArray;

    Scheduler(Semaphore timSem, Semaphore fin, Semaphore[] semArray){
        this.fin = fin;
        this.semArray = semArray;
        this.timSem = timSem;
    }

    @Override
    public void run() {
        try{

            while(true){
                timSem.acquire();
                int count = FinishData.timerCount;
                if(count == 160){
                    System.out.println("T1: " + FinishData.count1);
                    System.out.println("T2: " + FinishData.count2);
                    System.out.println("T3: " + FinishData.count3);
                    System.out.println("T4: " + FinishData.count4);
                    System.out.println("t1 over " + FinishData.over1);
                    System.out.println("t2 over " + FinishData.over2);
                    System.out.println("t3 over " + FinishData.over3);
                    System.out.println("t4 over " + FinishData.over4);
                    System.exit(0);
                }
                if(semArray[0].availablePermits() == 0){
                    semArray[0].release();
                }
                fin.acquire();
                if(!FinishData.isFinish1){
                    FinishData.over1++;
                }
                fin.release();
                if(count % 2 == 0){
                    if(semArray[1].availablePermits() == 0){
                        semArray[1].release();
                    }
                    fin.acquire();
                    if(!FinishData.isFinish2){
                        FinishData.over2++;
                    }
                    fin.release();
                }
                if(count % 4 == 0){
                    if(semArray[2].availablePermits() == 0){
                        semArray[2].release();
                    }
                    fin.acquire();
                    if(!FinishData.isFinish3){
                        FinishData.over3++;
                    }
                    fin.release();
                }
                if(count % 16 == 0){
                    if(semArray[3].availablePermits() == 0){
                        semArray[3].release();
                    }
                    fin.acquire();
                    if(!FinishData.isFinish4){
                        FinishData.over4++;
                    }
                    fin.release();
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
