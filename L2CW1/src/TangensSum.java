import java.util.*;

public class TangensSum {
    
    public static Random r = new Random();
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException{
        int threadCount;
        double suma = 0;
        double[] array = new double[100 * 10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextDouble();
        }
        System.out.println("Podaj liczbê w¹tków:");
        threadCount = sc.nextInt();
        SumaTangensow st[] = new SumaTangensow[threadCount];
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < st.length; i++) {
			double[] tmpArray = new double[array.length / threadCount];
			st[i] = new SumaTangensow(tmpArray);
			int begin = i * tmpArray.length;
			for (int j = 0; j < tmpArray.length; j++) {
				tmpArray[j] = array[begin];
				begin++;
			}
			st[i].start();
			
		}
        
        for (SumaTangensow s : st) {
			s.join();
			suma += s.getSum();
		}
        
        long stopTime = System.currentTimeMillis();
        long time = stopTime - startTime;
        System.out.println("Total is: " + suma);
        System.out.println("Elapsed time:" + time + " miliseconds");
    }
}

class SumaTangensow extends Thread implements Runnable {
    public static double[] array;
    public static double suma;

    public SumaTangensow(double[] a){
        
        array = a;
    }
    
    public static double getSum(){
        return suma;
    }
    
    @Override
    public void run() {
        
        
    	long startTime = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            suma += Math.tan(array[i]);
        }
        long stopTime = System.currentTimeMillis();
        System.out.println(" Total   i s :  " + suma);
        System.out.println("Elapsed time :  " + (stopTime - startTime) + " milisecund");
        
    }
    
    
}
