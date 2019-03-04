package xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class analysisXml {

    public static void main(String[] args) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("C:/student.xml"));
        //获取跟节点
        Element rootElement = document.getRootElement();
        getAllNode(rootElement);
    }

    public static void getAllNode(Element element){
        // 获取节点元素名
        System.out.println("\n当前节点元素名" + element.getName() + "解析开始··········");
        //当前节点文本数
        String stringValue = element.getTextTrim();
        if (stringValue != null && !stringValue.equals("")){
            System.out.println("当前节点值" + stringValue);
        }
        // 获取节点属性
        List<Attribute> attributes = element.attributes();
        for (Attribute attribute : attributes) {
            System.out.println("属性名" + attribute.getName() + "，属性值" + attribute.getValue());
        }
        System.out.println("当前节点元素名" + element.getName() + "解析结束··········\n");

        Iterator<Element> iterator = element.elementIterator();
        while (iterator.hasNext()){
            getAllNode(iterator.next());
        }
    }
}
