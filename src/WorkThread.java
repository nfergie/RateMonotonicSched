import java.util.concurrent.Semaphore;
import java.util.Arrays;

public class WorkThread extends Thread {
    private int times;
    private int type;
    private Semaphore fin;
    private Semaphore sem;
    private int [][] matrix = new int [10][10];

    WorkThread(Semaphore sem, Semaphore fin, int times, int type) {
        this.type = type;
        this.fin = fin;
        this.times = times;
        this.sem = sem;
        for(int [] row : this.matrix){
            Arrays.fill(row, 1);
        }

    }

    @Override
    public void run() {
        try{
            //noinspection InfiniteLoopStatement
            while(true){
                sem.acquire();
                fin.acquire();
                switch (type){
                    case 1:
                        FinishData.isFinish1 = false;
                        break;
                    case 2:
                        FinishData.isFinish2 = false;
                        break;
                    case 3:
                        FinishData.isFinish3 = false;
                        break;
                    case 4:
                        FinishData.isFinish4 = false;
                        break;
                }
                fin.release();
                int count =0;
                while(count < times){
                    doWork(matrix);
                    count++;
                }

                fin.acquire();
                switch (type){
                    case 1:
                        FinishData.isFinish1 = true;
                        FinishData.count1++;
                        break;
                    case 2:
                        FinishData.isFinish2 = true;
                        FinishData.count2++;
                        break;
                    case 3:
                        FinishData.isFinish3 = true;
                        FinishData.count3++;
                        break;
                    case 4:
                        FinishData.isFinish4 = true;
                        FinishData.count4++;
                        break;
                }
                fin.release();
            }


        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    private static void doWork(int[][] matrix){
        int result = matrix[0][0];
        for(int j =0; j < 5; j++){
            for(int i =0; i< 10; i++ ){
                result = result * matrix[i][j];
            }
            for(int k = 0; k < 10; k++){
                result = result * matrix[k][j+5];
            }
        }
    }
}
