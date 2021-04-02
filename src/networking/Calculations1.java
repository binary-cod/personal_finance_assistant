package networking;

public class Calculations1 extends Thread{
    public Calculations1(){
        try {
            System.out.println("I am runningg, and going to sleep!");
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
