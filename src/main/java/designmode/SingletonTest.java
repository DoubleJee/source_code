package designmode;

class Singleton {

    private static Singleton singleton;

    private Singleton() {

    }

    public static Singleton getSingleton() {
        //双重校验锁 懒汉单例
        if (singleton == null) {
            synchronized(Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}

public class SingletonTest {

    public static void main(String[] args) {
        Singleton s1 = Singleton.getSingleton();
        Singleton s2 = Singleton.getSingleton();

        System.out.println(s1 == s2);
    }
}
