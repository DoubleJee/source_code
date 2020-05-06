package embedded.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

public class EmbeddedTomcat {

    public static void main(String[] args) throws ServletException, LifecycleException {
        // 创建tomcat
        Tomcat tomcat = new Tomcat();
        // 监听端口号
        tomcat.setPort(8080);
        // 读取项目路径
        StandardContext ctx = (StandardContext) tomcat.addWebapp("/",new File("src/main").getAbsolutePath());
        // 禁止重新载入
        ctx.setReloadable(false);
        // class文件读取位置
        File additionWebInfClasses = new File("target/classes");
        // 创建web资源根
        WebResourceRoot resources = new StandardRoot(ctx);
        // tomcat内部读取class执行
        resources.addPreResources(new DirResourceSet(resources,"/target/classes",additionWebInfClasses.getAbsolutePath(),"/"));
        // 启动tomcat
        tomcat.start();
        // 异步等待请求执行
        tomcat.getServer().await();

    }
}
