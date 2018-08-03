package neo.tao.mvc.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * @Author neotao
 * @Date 2018/8/3
 * @Version V0.0.1
 * @Desc
 */
public class DispatcherServlet extends HttpServlet {

    private Properties contextConfig = new Properties();

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 1、加载配置
        loadConfigurations(config.getInitParameter("contextConfigLocation"));
        //2、扫描类
        loadAnnotationClasses(contextConfig.getProperty("componentScan.basePackage"));
        //3、初始化类
        initAnnotationClasses();
        //4、依赖注入
        doAutowire();
        //5、初始化请求路径
        initHandlerMapping();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }


    private void loadConfigurations(String contextConfigLocation) {
        InputStream configStream = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            contextConfig.load(configStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != configStream) {
                try {
                    configStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadAnnotationClasses(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                loadAnnotationClasses(scanPackage + "." + file.getName());
            } else {
                String className = (scanPackage + "." + file.getName().replace(".class", ""));
                classNames.add(className);
            }
        }
    }

    private void initAnnotationClasses() {

    }

    private void doAutowire() {

    }

    private void initHandlerMapping() {

    }


}
