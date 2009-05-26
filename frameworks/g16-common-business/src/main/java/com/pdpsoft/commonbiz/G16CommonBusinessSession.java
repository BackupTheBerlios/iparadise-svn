/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Dec 20, 2006
 * Time: 1:09:46 PM
 */
package com.pdpsoft.commonbiz;

import com.pdpsoft.business.BusinessException;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;


public interface G16CommonBusinessSession extends EJBObject {
    /**
     *
     * @param obj The object which is going to insert into the database.
     * @param isUpdate When is TRUE the update command will be invoked otherwise insert.
     * @param aspectName Aspect name of pre and post processes.
     * @throws BusinessException BusinessException
     * @throws RemoteException RemoteException
     */
    void insertObject(final Object obj, final Boolean isUpdate, final String aspectName) throws BusinessException, RemoteException;

    /**
     *
     * @param obj The object which is going to persist into the database.
     * @param aspectName Aspect name of pre and post processes.
     * @throws BusinessException BusinessException
     * @throws RemoteException RemoteException
     */
    void persistObject(final Object obj, final String aspectName) throws BusinessException, RemoteException;

    /**
     *
     * @param obj The object which is going to update.
     * @param aspectName Aspect name of pre and post processes.
     * @throws BusinessException BusinessException
     * @throws RemoteException RemoteException
     */
    void updateObject( final Object obj, final String aspectName) throws BusinessException, RemoteException;

    /**
     *
     * @param clazz The main class of which is going to be quried.
     * @param map An object for sending parameters to pre action to create Criterion
     * @param aspectName Aspect name of pre and post processes.
     * @return List of object which is queried.
     * @throws BusinessException BusinessException
     * @throws RemoteException RemoteException
     */
    List<?> getAllObjects(final Class clazz, final Map<Integer, Object> map, final String aspectName) throws BusinessException, RemoteException;

    /**
     *
     * @param clazz The main class of which is going to be quried.
     * @param pkValue Unique Identifier of object
     * @param aspectName Aspect name of pre and post processes.
     * @return An object insnatce of clazz
     * @throws BusinessException BusinessException
     * @throws RemoteException RemoteException
     */
    Object findByPrimaryKey( final Class clazz , final Integer pkValue, final String aspectName) throws BusinessException, RemoteException;

    /**
     * @deprecated
     */
    Object findByPrimaryKey( final Object obj, final String aspectName) throws BusinessException, RemoteException;

    /**
     * @deprecated
     */
    List<?> findByPrimaryKeys( final Class clazz , final List pkValues, final String aspectName) throws BusinessException, RemoteException;

    /**
     *
     * @param dataObject An object which is going to removed.
     * @param aspectName Aspect name of pre and post processes.
     * @throws BusinessException BusinessException
     * @throws RemoteException RemoteException
     */
    void removeObject( final Object dataObject, final String aspectName) throws BusinessException, RemoteException;

    /**
     *
     * @param dataObjects List of objects which are going to removed.
     * @param aspectName Aspect name of pre and post processes.
     * @throws BusinessException BusinessException
     * @throws RemoteException RemoteException
     */
    void removeObjects( final List dataObjects, final String aspectName) throws BusinessException, RemoteException;

    /**
     *
     * @param map An interface to send parameters.
     * @param aspectName Aspect name of pre and post processes.
     * @return Developer Demands !?!?! :)
     * @throws BusinessException BusinessException
     * @throws RemoteException RemoteException
     */
    Object process(Map<Integer, Object> map, final String aspectName) throws BusinessException, RemoteException;
}
