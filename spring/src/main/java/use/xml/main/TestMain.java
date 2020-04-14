package use.xml.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import use.xml.entity.UserEntity;

public class TestMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("learnXML.xml");
        UserEntity userEntity = classPathXmlApplicationContext.getBean("userEntity", UserEntity.class);
        System.out.println(userEntity);
    }
}
