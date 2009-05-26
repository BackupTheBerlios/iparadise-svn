package com.pdpsoft.commonbiz.util;

import com.pdpsoft.business.BusinessException;
import com.pdpsoft.hibernate.util.HibernateEXC;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Apr 29, 2008
 * Time: 8:17:21 PM
 */
public abstract class CommonBusinessCommandFactory {
    public enum CommonBusinessCommandEnum {
        FindByPKTypeOne, FindByPKTypeTwo, FindByPKTypeThree, GetAll,
        Insert, Persist, RemoveObject, Update, Process    
    }

    /**
     *
     * @param obj Arguments
     * @param commandEnum Determine what to do
     * @param aspectName Aspect name of pre and post processes.
     * @return result of commands
     * @throws BusinessException BusinessException
     */
    public static Object factory(Object obj, CommonBusinessCommandEnum commandEnum, final String aspectName) throws BusinessException {
        Object returnValue = null;
        G16AspectOrientedManagement aspectOriented = new G16AspectOrientedManagement(aspectName);
        try {


        if(commandEnum == CommonBusinessCommandEnum.FindByPKTypeOne)
            returnValue = aspectOriented.findByPrimaryKeyTypeOneCommand(obj);
/*
        if(commandEnum == CommonBusinessCommandEnum.FindByPKTypeTwo)
            command = new FindByPrimaryKeyTypeTwoCommonBusinessCommand();
        if(commandEnum == CommonBusinessCommandEnum.FindByPKTypeThree)
            command = new FindByPrimaryKeyTypeThreeCommonBusinessCommand();
*/
        if(commandEnum == CommonBusinessCommandEnum.GetAll)
            returnValue = aspectOriented.getAllCommand(obj);
        if(commandEnum == CommonBusinessCommandEnum.Insert)
            returnValue = aspectOriented.insertCommand(obj);
        if(commandEnum == CommonBusinessCommandEnum.Persist)
            returnValue = aspectOriented.persistCommand(obj);
        if(commandEnum == CommonBusinessCommandEnum.RemoveObject)
            returnValue = aspectOriented.removeCommand(obj);
        if(commandEnum == CommonBusinessCommandEnum.Update)
            returnValue = aspectOriented.updateCommand(obj);
        if(commandEnum == CommonBusinessCommandEnum.Process)
            returnValue = aspectOriented.process(obj);

        } catch (G16CommonBusinessException e) {
            throw e;
        } catch (HibernateEXC e) {
            throw new BusinessException(e.getMessage(), e, e.getErrNum());
        }
        return returnValue;
    }
}
