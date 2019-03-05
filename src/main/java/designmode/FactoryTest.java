package designmode;

//车类接口
interface Car{
    void run();
}
//奔驰车类
class BenChiCar implements Car{
    @Override
    public void run() {
        System.out.println("我是奔驰汽车！！跑起来！");
    }
}
//奥迪车类
class AoDiCar implements Car{

    @Override
    public void run() {
        System.out.println("我是奥迪汽车！！跑起来！");
    }
}
//车工厂类      在延伸可以各类车创建各自工厂
class CarFactory{
    public static Car createCar(String carName){
        Car car = null;
        switch (carName){
            case "奔驰":
                car = new BenChiCar();
                break;
            case "奥迪":
                car = new AoDiCar();
                break;
        }
        return car;
    }
}

public class FactoryTest {
    public static void main(String[] args) {
        //车工厂类创建车类对象 创建者与调用者分离
        Car benChiCar = CarFactory.createCar("奔驰");
        Car aoDiCar = CarFactory.createCar("奥迪");
        benChiCar.run();
        aoDiCar.run();
    }
}
