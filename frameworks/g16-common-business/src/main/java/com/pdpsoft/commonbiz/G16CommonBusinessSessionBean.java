/**
 * Ashna Company, G16
 * User: h_shayan & a_amini
 * Date: Feb 26, 2007
 * Time: 4:00:46 PM
 */
package com.pdpsoft.commonbiz;


import com.pdpsoft.business.BusinessException;
import com.pdpsoft.business.PersistenceManager;
import com.pdpsoft.business.PersistenceManagerFactory;
import com.pdpsoft.commonbiz.util.CommonBusinessCommandFactory;
import com.pdpsoft.commonbiz.util.impl.CommonBusinessCommandUtility;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class G16CommonBusinessSessionBean implements SessionBean {

    GenericPojoCommand command;

    public void ejbCreate() throws CreateException {
        command = GenericPojoCommand.getInstance();
    }

    public void setSessionContext(SessionContext sessionContext) throws EJBException {
    }

    public void ejbRemove() throws EJBException {
    }

    public void ejbActivate() throws EJBException {
    }

    public void ejbPassivate() throws EJBException {
    }

    /**
     *
     * @param obj The object which is going to insert into the database.
     * @param isUpdate When is TRUE the update command will be invoked otherwise insert.
     * @param aspectName Aspect name of pre and post processes.
     * @throws BusinessException BusinessException
     */
    public void insertObject(final Object obj, final Boolean isUpdate, final String aspectName) throws BusinessException {
        command.insertObject(obj, isUpdate, aspectName);
    }

    /**
     *
     * @param obj The object which is going to persist into the database.
     * @param aspectName Aspect name of pre and post processes.
     * @throws BusinessException BusinessException
     */
    public void persistObject(final Object obj, final String aspectName) throws BusinessException {
    	command.persistObject(obj, aspectName);
    }

    /**
     *
     * @param obj The object which is going to update.
     * @param aspectName Aspect name of pre and post processes.
     * @throws BusinessException BusinessException
     */
    public void updateObject(final Object obj, final String aspectName) throws BusinessException {
    	command.updateObject(obj, aspectName);
    }

    /**
     *
     * @param clazz The main class of which is going to be quried.
     * @param map An object for sending parameters to pre action to create Criterion
     * @param aspectName Aspect name of pre and post processes.
     * @return List of object which is queried.
     * @throws BusinessException BusinessException
     */
    public List<?> getAllObjects(final Class clazz, final Map<Integer, Object> map, final String aspectName) throws BusinessException {
    	return command.getAllObjects(clazz, map, aspectName);
    }

    /**
     *
     * @param clazz The main class of which is going to be quried.
     * @param pkValue Unique Identifier of object
     * @param aspectName Aspect name of pre and post processes.
     * @return An object insnatce of clazz
     * @throws BusinessException BusinessException
     */
    public Object findByPrimaryKey(final Class clazz, final Integer pkValue, final String aspectName) throws BusinessException {
        return command.findByPrimaryKey(clazz, pkValue, aspectName);
    }

    /**
     * @deprecated
     */
    public Object findByPrimaryKey(final Object obj, final String aspectName) throws BusinessException {
        return command.findByPrimaryKey(obj, aspectName);
    }

    /**
     * @deprecated
     */
    public List<?> findByPrimaryKeys(final Class clazz, final List pkValues, final String aspectName) throws BusinessException {
        return command.findByPrimaryKeys(clazz, pkValues, aspectName);
    }

    /**
     *
     * @param dataObject An object which is going to removed.
     * @param aspectName Aspect name of pre and post processes.
     * @throws BusinessException BusinessException
     */
    public void removeObject(final Object dataObject, final String aspectName) throws BusinessException {
    	command.removeObject(dataObject, aspectName);
    }

    /**
     *
     * @param dataObjects List of objects which are going to removed.
     * @param aspectName Aspect name of pre and post processes.
     * @throws BusinessException BusinessException
     */
    public void removeObjects(final List dataObjects, final String aspectName) throws BusinessException {
        for (Object dataObject : dataObjects) {
            removeObject(dataObject, aspectName);
        }
    }


    /**
     *
     * @param map An interface to send parameters.
     * @param aspectName Aspect name of pre and post processes.
     * @return Developer Demands !?!?! :)
     * @throws BusinessException BusinessException
     */
    public Object process(Map<Integer, Object> map, final String aspectName) throws BusinessException {
        return command.process(map, aspectName);
    }
}
