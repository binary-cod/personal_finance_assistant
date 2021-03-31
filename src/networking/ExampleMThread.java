package networking;

public class ExampleMThread {

    public static void main(String[] args) {
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i=0; i < 3; i++){
            Thread th  = new Thread(new Calculations(i));
            th.start();
        }
    }
}
