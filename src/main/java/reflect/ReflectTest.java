package reflect;

public class ReflectTest {

    public static void main(String[] args) throws Exception {

        Class<?> aClass = Class.forName("reflect.User");

        //反射机制 调用无参构造函数
        User user = (User) aClass.newInstance();

        //反射机制 调用有参构造函数
        User jeeUser = (User)aClass.getConstructor(int.class, String.class).newInstance(1, "Jee");

        System.out.println(user);
        System.out.println(jeeUser);

    }
}
