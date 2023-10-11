package indi.study.system.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ContextUtil implements ApplicationContextAware {

    /**
     * 保存ApplicationContext,可在项目中任意位置取出ApplicationContext
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //org.springframework.web.context.support.GenericWebApplicationContext
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     * @param name
     * @return
     */
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    /**
     * 通过class获取class下的全部Bean.
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> Map<String, T> getBeans(Class<T> clazz){
        return applicationContext.getBeansOfType(clazz);
    }

    /**
     * 通过class获取Bean.
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }
}

