package mybaits;

import java.lang.reflect.Field;

@Table(table = "employee")
public class Employee {
    @Property(name = "id")
    private Long id;
    @Property(name = "real_name")
    private String realName;
    @Property(name = "mobile")
    private String mobile;

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("mybaits.Employee");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select ");
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            //获取字段级的数据库列注解
            Property property = declaredField.getDeclaredAnnotation(Property.class);
            //拼接sql语句
            stringBuffer.append(property.name() + ",");
        }
        stringBuffer.deleteCharAt(stringBuffer.length()-1);

        //获取类级别的表注解
        Table table = aClass.getDeclaredAnnotation(Table.class);
        //拼接sql语句
        stringBuffer.append(" from " + table.table());
        System.out.println(stringBuffer.toString());
    }
}
