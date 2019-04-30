import java.util.concurrent.Semaphore;
import java.util.Arrays;

public class WorkThread extends Thread {
    FinishData data;
    int times;
    int type;
    Semaphore fin;
    Semaphore sem;
    int [][] matrix = new int [10][10];

    public WorkThread(Semaphore sem, Semaphore fin, FinishData data, int times, int type) {
        this.data = data;
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
            while(true){
                sem.acquire();
                System.out.println(this.getName() + "Running");
                Thread.sleep(100);
                int count =0;
                while(count < times){
                    doWork(matrix);
                    count++;
                }
//
//                fin.acquire();
//                switch (type){
//                    case 1:
//                        data.isFinish1 = true;
//                        data.count1++;
//                        break;
//                    case 2:
//                        data.isFinish2 = true;
//                        data.count2++;
//                        break;
//                    case 3:
//                        data.isFinish3 = true;
//                        data.count3++;
//                        break;
//                    case 4:
//                        data.isFinish4 = true;
//                        data.count4++;
//                        break;
//                }
//                fin.release();
            }


        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    static void doWork(int[][] matrix){
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
