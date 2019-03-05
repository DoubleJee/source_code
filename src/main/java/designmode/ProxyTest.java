package designmode;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//甲方 房子
interface House{
    void buyHouse();
}
//委托方 人
class People implements House{

    @Override
    public void buyHouse() {
        System.out.println("我终于可以买房了！");
    }
}
//中间人 中介 （代理）
class HouseProxy implements House {
    //委托方
    private People people;

    public HouseProxy(People people) {
        this.people = people;
    }

    @Override
    public void buyHouse() {
        System.out.println("你把钱给我，房子交给我来搞定吧....");
        //委托的方法
        people.buyHouse();
        System.out.println("房子搞定了，这是你的钥匙....");
    }

}

//JDK动态代理
class JDKProxy implements InvocationHandler{
    private Object object;
    public JDKProxy(Object object){
        this.object = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //开始代理
        System.out.println("你把钱给我，房子交给我来搞定吧....");
        //调用委托方方法
        Object invoke = method.invoke(object, args);
        System.out.println("房子搞定了，这是你的钥匙....");
        return invoke;
    }
}
//cglib动态代理
class CgLibProxy implements MethodInterceptor{


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        //开始代理
        System.out.println("你把钱给我，房子交给我来搞定吧....");
        //调用委托方方法
        Object invoke = proxy.invokeSuper(obj, args);
        System.out.println("房子搞定了，这是你的钥匙....");
        return invoke;
    }
}

public class ProxyTest {
    public static void main(String[] args) {
        /**
         * 静态代理
         */
        //委托方
        People gzz = new People();
        //代理
        HouseProxy proxy = new HouseProxy(gzz);
        //代理帮甲方去购买乙方房子
        //前后细节代理来做 AOP动态代理
        proxy.buyHouse();


        /**
         * JDK动态代理
         */
        //委托方
        People gzz2 = new People();
        JDKProxy jdkProxy = new JDKProxy(gzz2);
        House houser = (House) Proxy.newProxyInstance(gzz2.getClass().getClassLoader(), gzz2.getClass().getInterfaces(), jdkProxy);
        houser.buyHouse();

        /**
         * cglib动态代理
         */

        //代理类
        CgLibProxy cgLibProxy = new CgLibProxy();
        //委托方
        People gzz3 = new People();
        //代理增强者
        Enhancer enhancer = new Enhancer();
        //设置委托方
        enhancer.setSuperclass(People.class);
        //设置代理方
        enhancer.setCallback(cgLibProxy);
        //代理出来的对象
        House cbHouse = (House) enhancer.create();
        cbHouse.buyHouse();

    }
}
