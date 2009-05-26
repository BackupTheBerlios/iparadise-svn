package com.pdpsoft.web.common.commands;

import com.pdpsoft.g16.business.BusinessException;
import com.pdpsoft.g16.business.BusinessInvocationException;
import com.pdpsoft.web.BusinessDelegate;
import com.pdpsoft.web.ServiceLocatorCommand;
import com.pdpsoft.web.configuration.ServiceLocatorFactory;
import com.pdpsoft.web.configuration.WebConfiguration;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: h_shayan, a_heydari
 * Date: 04-Jun-2008
 * Time: 15:52:05
 */
public class G16GenericWebCommand {
    public static final String G16_COMMON_BUSINESS = "G16CommonBusinessSessionEJB";
    public static final String GENERIC_ASPECT_NAME = "g16";

    public static final String ENTITY = "entity";
    public static final String PARAMETER = "parameter";
    public static final String ASPECT_NAME = "aspectName";

    public static final String ACTIONFORM_PROP_NAME = "actionformMethodName";


    public static ServiceLocatorCommand getBusinessDelegate() {
        return ServiceLocatorFactory.factory(WebConfiguration.getInstance().getServiceLocatorType());
    }

    public static Object defaultInvokeBusiness(final GenericCommandsEnum command, List<Object> arg) throws BusinessException, BusinessInvocationException {
        return getBusinessDelegate().invokeBusiness(G16_COMMON_BUSINESS, methodFactory(command), arg);
    }


    public static String getG16CommonBusiness() {
        return G16_COMMON_BUSINESS;
    }

    public static String getGenericAspectName() {
        return GENERIC_ASPECT_NAME;
    }

    public enum GenericCommandsEnum {
        insertObject, persistObject, getAllObjects, findByPrimaryKey, removeObject, process
    }

    public static String methodFactory(final GenericCommandsEnum command) {
        String commandName = "";
        if (command.equals(GenericCommandsEnum.findByPrimaryKey))
            commandName = "findByPrimaryKey";
        if (command.equals(GenericCommandsEnum.getAllObjects))
            commandName = "getAllObjects";
        if (command.equals(GenericCommandsEnum.insertObject))
            commandName = "insertObject";
        if (command.equals(GenericCommandsEnum.persistObject))
            commandName = "persistObject";
        if (command.equals(GenericCommandsEnum.process))
            commandName = "process";
        if (command.equals(GenericCommandsEnum.removeObject))
            commandName = "removeObject";
        return commandName;
    }
}
