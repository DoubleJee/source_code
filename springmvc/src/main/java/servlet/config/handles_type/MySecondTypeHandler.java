package servlet.config.handles_type;

//第二种处理器类
public class MySecondTypeHandler implements MyHandlerType{
    @Override
    public void handle() {
        System.out.println("第二种处理类开始处理");
    }
}
