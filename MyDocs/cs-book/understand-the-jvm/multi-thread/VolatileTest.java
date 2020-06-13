/**
 * 结果是略小于 20 *10000 的数值
 */

public class Temp {

    public static volatile int race = 0;

    public static void increase(){
        race ++;
    }

    private static final int COUNTS=  20;

    public static void main(String[] args) {

        for (int i = 0; i < COUNTS; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        increase();
                        System.out.println(race);
                    }
                }
            }).start();
        }

        System.out.println("race end");
        //直接卡死，不会执行到这里，哪怕线程里都执行完了
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        System.out.println(race);
    }
}
