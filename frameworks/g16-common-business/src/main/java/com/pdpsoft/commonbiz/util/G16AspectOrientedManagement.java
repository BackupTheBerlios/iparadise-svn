package com.pdpsoft.commonbiz.util;

import com.pdpsoft.business.BusinessException;
import com.pdpsoft.commonbiz.util.applicationcontex.G16ApplicationContext;
import com.pdpsoft.hibernate.util.HibernateEXC;
import org.hibernate.criterion.Criterion;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: May 28, 2008
 * Time: 10:35:11 AM
 */
public class G16AspectOrientedManagement {
    ApplicationContext context = G16ApplicationContext.getInstance().getContext();
    /**
     * aspectName is a name that developer should pass
     * through method calling and use it in applicationContext.xml
     * file(s).
     */
    private String aspectName;


    public G16AspectOrientedManagement() {
    }

    /**
     *
     * @param aspectName Aspect name of pre and post processes.
     */
    public G16AspectOrientedManagement(String aspectName) {
        this.aspectName = aspectName;
    }

    /**
     *
     * @param obj Argument
     * @return Result of command
     * @throws BusinessException BusinessException
     * @throws HibernateEXC HibernateEXC
     */
    public Object process(Object obj) throws BusinessException, HibernateEXC {
        Object resultValue = null;
        final String aspect = "Process";
        G16CommonBusinessCommand businessCommand = (G16CommonBusinessCommand) G16ApplicationContext.getInstance().getContext().getBean("g16" + aspect + getAspectName());
        final String preBeanName = "pre" + aspect + String.valueOf(getAspectName().charAt(0)).toUpperCase() + getAspectName().substring(1);
        final String postBeanName = "post" + aspect + String.valueOf(getAspectName().charAt(0)).toUpperCase() + getAspectName().substring(1);

        Object preBean = null, postBean = null;
        if(context.containsBean(preBeanName))
            preBean = context.getBean(preBeanName);
        if(context.containsBean(postBeanName))
            postBean = context.getBean(postBeanName);

        if(preBean == null)
                resultValue = businessCommand.execute(obj);
        else {
            Boolean shouldRunCommand = (Boolean) ((G16CommonBusinessCommand)preBean).execute(obj);
            if(shouldRunCommand)
                resultValue = businessCommand.execute(obj);
        }
        Map<Integer, Object> map = new HashMap<Integer, Object>(2);
        map.put(0, obj);
        map.put(1, resultValue);
        if(postBean != null)
            ((G16CommonBusinessCommand)postBean).execute(map);
        return resultValue;
    }

    /**
     *
     * @param obj Argument
     * @return Result of command
     * @throws BusinessException BusinessException
     * @throws HibernateEXC HibernateEXC
     */
    public Object insertCommand(Object obj) throws BusinessException, HibernateEXC {
        final String aspect = "Insert";
        G16CommonBusinessCommand businessCommand = (G16CommonBusinessCommand) G16ApplicationContext.getInstance().getContext().getBean("g16" + aspect);
        final String preBeanName = "pre" + aspect + String.valueOf(getAspectName().charAt(0)).toUpperCase() + getAspectName().substring(1);
        final String postBeanName = "post" + aspect + String.valueOf(getAspectName().charAt(0)).toUpperCase() + getAspectName().substring(1);

        Object preBean = null, postBean = null;
        if(context.containsBean(preBeanName))
            preBean = context.getBean(preBeanName);
        if(context.containsBean(postBeanName))
            postBean = context.getBean(postBeanName);

        if(preBean == null)
                businessCommand.execute(obj);
        else {
            Boolean shouldRunCommand = (Boolean) ((G16CommonBusinessCommand)preBean).execute(obj);
            if(shouldRunCommand)
                businessCommand.execute(obj);
        }
        if(postBean != null)
            ((G16CommonBusinessCommand)postBean).execute(obj);
        return obj;
    }

    /**
     *
     * @param obj Argument
     * @return Result of command
     * @throws BusinessException BusinessException
     * @throws HibernateEXC HibernateEXC
     */
    public Object persistCommand(Object obj) throws BusinessException, HibernateEXC {
        final String aspect = "Persist";
        G16CommonBusinessCommand businessCommand = (G16CommonBusinessCommand) G16ApplicationContext.getInstance().getContext().getBean("g16" + aspect);
        final String preBeanName = "pre" + aspect + String.valueOf(getAspectName().charAt(0)).toUpperCase() + getAspectName().substring(1);
        final String postBeanName = "post" + aspect + String.valueOf(getAspectName().charAt(0)).toUpperCase() + getAspectName().substring(1);

        Object preBean = null, postBean = null;
        if(context.containsBean(preBeanName))
            preBean = context.getBean(preBeanName);
        if(context.containsBean(postBeanName))
            postBean = context.getBean(postBeanName);

        if(preBean == null)
                businessCommand.execute(obj);
        else {
            Boolean shouldRunCommand = (Boolean) ((G16CommonBusinessCommand)preBean).execute(obj);
            if(shouldRunCommand)
                businessCommand.execute(obj);
        }
        if(postBean != null)
            ((G16CommonBusinessCommand)postBean).execute(obj);
        return obj;
    }

    /**
     *
     * @param obj Argument
     * @return Result of command
     * @throws BusinessException BusinessException
     * @throws HibernateEXC HibernateEXC
     */
    public Object updateCommand(Object obj) throws BusinessException, HibernateEXC {
        final String aspect = "Update";
        G16CommonBusinessCommand businessCommand = (G16CommonBusinessCommand) G16ApplicationContext.getInstance().getContext().getBean("g16" + aspect);
        final String preBeanName = "pre" + aspect + String.valueOf(getAspectName().charAt(0)).toUpperCase() + getAspectName().substring(1);
        final String postBeanName = "post" + aspect + String.valueOf(getAspectName().charAt(0)).toUpperCase() + getAspectName().substring(1);

        Object preBean = null, postBean = null;
        if(context.containsBean(preBeanName))
            preBean = context.getBean(preBeanName);
        if(context.containsBean(postBeanName))
            postBean = context.getBean(postBeanName);

        if(preBean == null)
                businessCommand.execute(obj);
        else {
            Boolean shouldRunCommand = (Boolean) ((G16CommonBusinessCommand)preBean).execute(obj);
            if(shouldRunCommand)
                businessCommand.execute(obj);
        }
        if(postBean != null)
            ((G16CommonBusinessCommand)postBean).execute(obj);
        return obj;
    }

    /**
     *
     * @param obj Argument
     * @return Result of command
     * @throws BusinessException BusinessException
     * @throws HibernateEXC HibernateEXC
     */
    public Object findByPrimaryKeyTypeOneCommand(Object obj) throws BusinessException, HibernateEXC {
        final String aspect = "FindByPrimaryKeyTypeOne";
        G16CommonBusinessCommand businessCommand = (G16CommonBusinessCommand) G16ApplicationContext.getInstance().getContext().getBean("g16" + aspect);
        final String preBeanName = "pre" + aspect + String.valueOf(getAspectName().charAt(0)).toUpperCase() + getAspectName().substring(1);
        final String postBeanName = "post" + aspect + String.valueOf(getAspectName().charAt(0)).toUpperCase() + getAspectName().substring(1);

        Object preBean = null, postBean = null;
        if(context.containsBean(preBeanName))
            preBean = context.getBean(preBeanName);
        if(context.containsBean(postBeanName))
            postBean = context.getBean(postBeanName);

        Object returnValue = null;

        if(preBean == null)
                returnValue = businessCommand.execute(obj);
        else {
            Boolean shouldRunCommand = (Boolean) ((G16CommonBusinessCommand)preBean).execute(obj);
            if(shouldRunCommand)
                returnValue = businessCommand.execute(obj);
        }
        if(postBean != null)
            ((G16CommonBusinessCommand)postBean).execute(returnValue);
        return returnValue;
    }

    /**
     *
     * @param obj Argument
     * @return Result of command
     * @throws BusinessException BusinessException
     * @throws HibernateEXC HibernateEXC
     */
    public Object removeCommand(Object obj) throws BusinessException, HibernateEXC {
        final String aspect = "Remove";
        G16CommonBusinessCommand businessCommand = (G16CommonBusinessCommand) G16ApplicationContext.getInstance().getContext().getBean("g16" + aspect);
        final String preBeanName = "pre" + aspect + String.valueOf(getAspectName().charAt(0)).toUpperCase() + getAspectName().substring(1);
        final String postBeanName = "post" + aspect + String.valueOf(getAspectName().charAt(0)).toUpperCase() + getAspectName().substring(1);

        Object preBean = null, postBean = null;
        if(context.containsBean(preBeanName))
            preBean = context.getBean(preBeanName);
        if(context.containsBean(postBeanName))
            postBean = context.getBean(postBeanName);

        Object returnValue = null;

        if(preBean == null)
                returnValue = businessCommand.execute(obj);
        else {
            Boolean shouldRunCommand = (Boolean) ((G16CommonBusinessCommand)preBean).execute(obj);
            if(shouldRunCommand)
                returnValue = businessCommand.execute(obj);
        }
        if(postBean != null)
            ((G16CommonBusinessCommand)postBean).execute(returnValue);
        return returnValue;
    }

    /**
     *
     * @param obj Argument
     * @return Result of command
     * @throws BusinessException BusinessException
     * @throws HibernateEXC HibernateEXC
     */
    public Object getAllCommand(Object obj) throws BusinessException, HibernateEXC {
        Map<Integer, Object> mapParam = (Map<Integer, Object>) obj;
        obj = mapParam.get(0);
        final String aspect = "GetAll";
        Object returnValue = null;
        G16CommonBusinessCommand businessCommand = (G16CommonBusinessCommand) G16ApplicationContext.getInstance().getContext().getBean("g16" + aspect);
        final String preBeanName = "pre" + aspect + String.valueOf(getAspectName().charAt(0)).toUpperCase() + getAspectName().substring(1);
        final String postBeanName = "post" + aspect + String.valueOf(getAspectName().charAt(0)).toUpperCase() + getAspectName().substring(1);

        Object preBean = null, postBean = null;
        if(context.containsBean(preBeanName))
            preBean = context.getBean(preBeanName);
        if(context.containsBean(postBeanName))
            postBean = context.getBean(postBeanName);

        Map<Integer, Object> map = new HashMap<Integer, Object>(2);
        map.put(0, obj);
        map.put(1, null);

        if(preBean == null)
                returnValue = businessCommand.execute(map);
        else {
            Criterion criterion = (Criterion) ((G16CommonBusinessCommand)preBean).execute(mapParam.get(1));
            map.put(1, criterion);
            returnValue = businessCommand.execute(map);
        }
        if(postBean != null)
            ((G16CommonBusinessCommand)postBean).execute(obj);
        return returnValue;
    }

    /**
     *
     * @return aspect name
     */
    public String getAspectName() {
        return aspectName;
    }

    /**
     *
     * @param aspectName aspect name
     */
    public void setAspectName(String aspectName) {
        this.aspectName = aspectName;
    }
}
