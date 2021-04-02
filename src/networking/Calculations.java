package networking;

public class Calculations implements Runnable{
    private int count;
    public Calculations(int count){
        this.count = count;
    }
    @Override
    public void run() {
        System.out.println("I am a runnable "+ count+" running inside a thread");
        try {
            Thread.sleep(50000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
