package rpc;

import rpc.server.service.RpcService;
import rpc.server.service.impl.RpcServiceImpl;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

class JDKProxy implements InvocationHandler {

    private Class target;

    public JDKProxy(Class target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Socket socket = new Socket("127.0.0.1",8080);
        Class<?> aClass = target;
        Class<?>[] parameterTypes = method.getParameterTypes();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream.writeUTF(aClass.getName());
        objectOutputStream.writeUTF(method.getName());
        objectOutputStream.writeObject(parameterTypes);
        objectOutputStream.writeObject(args);
        Object object = objectInputStream.readObject();
        objectOutputStream.close();
        objectInputStream.close();
        return object;
    }
}

public class RpcClient {

    public static void main(String[] args) {
        RpcService rpcService = (RpcService) Proxy.newProxyInstance(RpcServiceImpl.class.getClassLoader(),RpcServiceImpl.class.getInterfaces(),new JDKProxy(RpcServiceImpl.class));
        System.out.println(rpcService.rpcMethod("name","小明"));
    }
}
