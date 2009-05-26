package com.pdpsoft.commonbiz.util.applicationcontex;

import com.pdpsoft.commonbiz.util.G16CommonBusinessCommand;
import org.springframework.context.ApplicationContext;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: May 29, 2008
 * Time: 3:19:02 PM
 */
public class G16ApplicationContextBeans {
    static ApplicationContext context = G16ApplicationContext.getInstance().getContext();

    /**
     *
     * @param beanName Bean name in application context
     * @return An instance of G16CommonBusinessCommand
     */
    public static G16CommonBusinessCommand getBean(final String beanName) {
        return (G16CommonBusinessCommand) context.getBean(beanName);
    }

    /**
     *
     * @return An instance of G16CommonBusinessCommand for insert
     */
    public static G16CommonBusinessCommand getG16Insert() {
        return getBean("g16Insert");
    }
    /**
     *
     * @return An instance of G16CommonBusinessCommand for persist
     */
    public static G16CommonBusinessCommand getG16Persist() {
        return getBean("g16Persist");
    }
    /**
     *
     * @return An instance of G16CommonBusinessCommand for update
     */
    public static G16CommonBusinessCommand getG16Update() {
        return getBean("g16Update");
    }
    /**
     *
     * @return An instance of G16CommonBusinessCommand for get all
     */
    public static G16CommonBusinessCommand getG16Getall() {
        return getBean("g16GetAll");
    }
    /**
     *
     * @return An instance of G16CommonBusinessCommand for find by primary key type one
     */
    public static G16CommonBusinessCommand getG16FindByPrimaryKeyTypeOne() {
        return getBean("g16FindByPrimaryKeyTypeOne");
    }
    /**
     *
     * @return An instance of G16CommonBusinessCommand for remove
     */
    public static G16CommonBusinessCommand getG16Remove() {
        return getBean("g16Remove");
    }
}
