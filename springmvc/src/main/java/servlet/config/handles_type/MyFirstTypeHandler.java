package servlet.config.handles_type;

//第一种处理器类
public class MyFirstTypeHandler implements MyHandlerType{
    @Override
    public void handle() {
        System.out.println("第一种处理类开始处理");
    }
}
