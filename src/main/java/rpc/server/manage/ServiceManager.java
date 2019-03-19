package rpc.server.manage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceManager {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true){
            Socket acceptSocket = serverSocket.accept();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    ObjectInputStream objectInputStream = null;
                    ObjectOutputStream objectOutputStream = null;
                    try {
                        objectInputStream = new ObjectInputStream(acceptSocket.getInputStream());
                        objectOutputStream = new ObjectOutputStream(acceptSocket.getOutputStream());
                        //获取接口名
                        String interfaceName = objectInputStream.readUTF();
                        //获取类
                        Class<?> targetClass = Class.forName(interfaceName);
                        //获取方法名
                        String methodName = objectInputStream.readUTF();
                        //获取方法名参数类型
                        Class<?>[] paramsType = (Class<?>[])objectInputStream.readObject();
                        //获取方法实例
                        Method method = targetClass.getDeclaredMethod(methodName,paramsType);
                        //获取方法参数
                        Object[] params = (Object[])objectInputStream.readObject();
                        //反射机制调用
                        Object invoke = method.invoke(targetClass.newInstance(), params);
                        //返回结果
                        objectOutputStream.writeObject(invoke);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            acceptSocket.close();
                            objectInputStream.close();
                            objectOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
