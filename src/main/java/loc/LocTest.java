package loc;

import reflect.User;

public class LocTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user1 = (User) classPathXmlApplicationContext.getBean("user1");
        User user2 = (User) classPathXmlApplicationContext.getBean("user2");
        System.out.println(user1);
        System.out.println(user2);

        BeanClassPathXmlApplicationContext beanClassPathXmlApplicationContext = new BeanClassPathXmlApplicationContext("applicationContext.xml");
        User newUser = (User) beanClassPathXmlApplicationContext.getBean("user1");
        System.out.println(newUser);
    }
}
