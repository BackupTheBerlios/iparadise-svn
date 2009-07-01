/**
 * PDP Company, G16
 * User: h_shayan
 * Date: Feb 21, 2009
 * Time: 2:43:46 PM
 */
package com.pdpsoft.commonbiz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pdpsoft.business.BusinessException;
import com.pdpsoft.business.PersistenceManagerFactory;
import com.pdpsoft.commonbiz.util.CommonBusinessCommandFactory;
import com.pdpsoft.commonbiz.util.impl.CommonBusinessCommandUtility;

public class GenericPojoCommand {
	
	private static final Log logger = LogFactory.getLog(GenericPojoCommand.class);
		
	private GenericPojoCommand() {}
	
	private static class GenericPojoCommandHolder {
		private static GenericPojoCommand command = new GenericPojoCommand();		
		static {
			logger.info("Persistence Manager is initializing......");
			PersistenceManagerFactory.getDefault();
			logger.info("GenericPojoCommand is initialized.");
		}
	}
	
	public static GenericPojoCommand getInstance() {
		return GenericPojoCommandHolder.command;
	}
	
    /**
    *
    * @param obj The object which is going to insert into the database.
    * @param isUpdate When is TRUE the update command will be invoked otherwise insert.
    * @param aspectName Aspect name of pre and post processes.
    * @throws BusinessException BusinessException
    */
   public void insertObject(final Object obj, final Boolean isUpdate, final String aspectName) throws BusinessException {
       Map<Integer, Object> map = new HashMap<Integer, Object>(2);
       map.put(CommonBusinessCommandUtility.InsertCommandEnumeration.OBJECT, obj);
       map.put(CommonBusinessCommandUtility.InsertCommandEnumeration.IS_UPDATE, isUpdate);
       CommonBusinessCommandFactory.factory(map, CommonBusinessCommandFactory.CommonBusinessCommandEnum.Insert, aspectName);
   }

   /**
    *
    * @param obj The object which is going to persist into the database.
    * @param aspectName Aspect name of pre and post processes.
    * @throws BusinessException BusinessException
    */
   public void persistObject(final Object obj, final String aspectName) throws BusinessException {
       CommonBusinessCommandFactory.factory(obj, CommonBusinessCommandFactory.CommonBusinessCommandEnum.Persist, aspectName);
   }

   /**
    *
    * @param obj The object which is going to update.
    * @param aspectName Aspect name of pre and post processes.
    * @throws BusinessException BusinessException
    */
   public void updateObject(final Object obj, final String aspectName) throws BusinessException {
       CommonBusinessCommandFactory.factory(obj, CommonBusinessCommandFactory.CommonBusinessCommandEnum.Update, aspectName);
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
       Map<Integer, Object> mapParam = new HashMap<Integer, Object>(2);
       mapParam.put(0, clazz);
       mapParam.put(1, map);
       return (List<?>) CommonBusinessCommandFactory.factory(mapParam, CommonBusinessCommandFactory.CommonBusinessCommandEnum.GetAll, aspectName);
   }

   /**
    *
    * @param clazz The main class of which is going to be quried.
    * @param pkValue Unique Identifier of object
    * @param aspectName Aspect name of pre and post processes.
    * @return An object insnatce of clazz
    * @throws BusinessException BusinessException
    */
   public Object findByPrimaryKey(final Class clazz, final Object pkValue, final String aspectName) throws BusinessException {
       Map<Integer, Object> map = new HashMap<Integer, Object>(2);
       map.put(CommonBusinessCommandUtility.FindByPrimaryKeyCommandEnumeration.CLASS, clazz);
       map.put(CommonBusinessCommandUtility.FindByPrimaryKeyCommandEnumeration.PK_VALUE, pkValue);
       return CommonBusinessCommandFactory.factory(map, CommonBusinessCommandFactory.CommonBusinessCommandEnum.FindByPKTypeOne, aspectName);
   }

   /**
    * @deprecated
    */
   public Object findByPrimaryKey(final Object obj, final String aspectName) throws BusinessException {
       return CommonBusinessCommandFactory.factory(obj, CommonBusinessCommandFactory.CommonBusinessCommandEnum.FindByPKTypeTwo, aspectName);
   }

   /**
    * @deprecated
    */
   public List<?> findByPrimaryKeys(final Class clazz, final List pkValues, final String aspectName) throws BusinessException {
       Map<Integer, Object> map = new HashMap<Integer, Object>(2);
       map.put(CommonBusinessCommandUtility.FindByPrimaryKeyCommandEnumeration.CLASS, clazz);
       map.put(CommonBusinessCommandUtility.FindByPrimaryKeyCommandEnumeration.PK_VALUE, pkValues);
       return (List<?>) CommonBusinessCommandFactory.factory(map, CommonBusinessCommandFactory.CommonBusinessCommandEnum.FindByPKTypeThree, aspectName);
   }

   /**
    *
    * @param dataObject An object which is going to removed.
    * @param aspectName Aspect name of pre and post processes.
    * @throws BusinessException BusinessException
    */
   public void removeObject(final Object dataObject, final String aspectName) throws BusinessException {
       CommonBusinessCommandFactory.factory(dataObject, CommonBusinessCommandFactory.CommonBusinessCommandEnum.RemoveObject, aspectName);
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
       return CommonBusinessCommandFactory.factory(map, CommonBusinessCommandFactory.CommonBusinessCommandEnum.Process, aspectName);
   }

}
