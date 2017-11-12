package net.hehe.utils;

import java.lang.reflect.Field;

/**
 * @Author neo·tao
 * @Date 2017/11/12
 * @Desc
 */
public class ServiceFactory {
    private Class<?> clazz;
    public ServiceFactory(String packagePath) {
        Package pkg = Package.getPackage(packagePath);
//        pkg.get
    }

    public static void initService(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);

        }
    }
}
