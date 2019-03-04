package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {

    public static void main(String[] args) throws Exception {

        Class<?> aClass = Class.forName("reflect.User");

        //反射机制 调用无参构造函数
        User user = (User) aClass.newInstance();

        //反射机制 调用有参构造函数
        User jeeUser = (User)aClass.getConstructor(int.class, String.class).newInstance(1, "Jee");
        System.out.println(user);
        System.out.println(jeeUser);


        //获取当前类的所有声明的字段
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            //字段名
            System.out.println(declaredField.getName());
        }
        //获取当前类的所有声明的方法
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            //方法名
            System.out.println(declaredMethod.getName());
        }

        User privateUser = (User) aClass.newInstance();
        //获取类声明的指定字段
        Field id = aClass.getDeclaredField("id");
        Field name = aClass.getDeclaredField("name");
        //获取类声明的指定方法
        Method add = aClass.getDeclaredMethod("add", Integer.class, Integer.class);

        //设置私有访问权限
        id.setAccessible(true);
        name.setAccessible(true);
        add.setAccessible(true);
        //给对象字段赋值
        id.set(privateUser,99);
        name.set(privateUser,"宇宙人物");
        //调用对象方法 带参并返回值
        Integer sum = (Integer) add.invoke(privateUser, 1, 2);
        System.out.println(privateUser + sum.toString());

    }
}
