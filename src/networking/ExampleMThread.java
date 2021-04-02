package networking;

public class ExampleMThread {

    public static void main(String[] args) {
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread exampleThread = new Calculations1();
        exampleThread.start();

        for (int i=0; i < 3; i++){
            Thread th  = new Thread(new Calculations(i));
            th.start();
        }
    }
}
