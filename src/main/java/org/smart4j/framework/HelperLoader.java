package org.smart4j.framework;

import org.smart4j.framework.helper.*;
import org.smart4j.framework.util.ClassUtil;

/**
 * 加载相应的 Helper 类
 * @author Ther
 */
public final class HelperLoader {
    public static void init() {
        // AopHelper 要在 IocHelper 之前加载，
        // 因为首先要通过 AopHelper 获取代理对象，然后才能通过 IocHelper 进行依赖注入
        Class<?>[] classArray = {
            ClassHelper.class,
            BeanHelper.class,
            AopHelper.class,
            IocHelper.class,
            ControllerHelper.class
        };
        for (Class<?> cls : classArray) {
            ClassUtil.loadClass(cls.getName(), true);
        }
    }
}
