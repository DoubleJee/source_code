package loc;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanClassPathXmlApplicationContext {

    private Document xmlDocument;
    private static final String ID = "id";
    private static final String CLASS = "class";
    private static final String NAME = "name";
    private static final String VALUE = "value";
    private static volatile Map<String, Object> map = new ConcurrentHashMap<>();

    public BeanClassPathXmlApplicationContext(String path) {
        initBean(path);
    }

    private void initBean(String path) {
        SAXReader saxReader = new SAXReader();
        //解析xml文件
        try {
            xmlDocument = saxReader.read(this.getClass().getClassLoader().getResource(path));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootElement = xmlDocument.getRootElement();
        //获取根节点下所有bean节点
        List<Element> elements = rootElement.elements();
        try {
            for (Element element : elements) {
                String beanId = element.attributeValue(ID);
                //获取bean节点的class值
                String classValue = element.attributeValue(CLASS);
                //获取class对象
                Class<?> aClass = Class.forName(classValue);
                //反射机制实例化对象
                Object bean = aClass.newInstance();

                List<Element> nodeElements = element.elements();
                for (Element nodeElement : nodeElements) {
                    //获取name属性的字段名称
                    String name = nodeElement.attributeValue(NAME);
                    //获取value属性的字段值
                    String value = nodeElement.attributeValue(VALUE);
                    //获取字段对象
                    Field nameField = aClass.getDeclaredField(name);
                    //设置字段私有访问权限
                    nameField.setAccessible(true);
                    //设置字段值
                    if (nameField.getType().equals(int.class))
                        nameField.set(bean, Integer.parseInt(value));
                    if (nameField.getType().equals(String.class))
                        nameField.set(bean, value);
                }
                map.put(beanId, bean);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String beanId) {
        return map.get(beanId);
    }
}
