package com.pdpsoft.web.common.commands;

import com.pdpsoft.g16.business.BusinessException;
import com.pdpsoft.g16.business.BusinessInvocationException;
import com.pdpsoft.commons.G16ParentEntity;
import com.pdpsoft.web.common.G16ChainContext;
import com.pdpsoft.web.common.G16WebCommand;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.Command;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: h_shayan, a_heydari
 * Date: 04-Jun-2008
 * Time: 15:50:11
 */
public class GetAllWebCommand extends G16GenericWebCommand implements G16WebCommand {
    public boolean execute(Context context) throws Exception {
        G16ChainContext g16ChainContext = (G16ChainContext) context;

        G16ParentEntity g16ParentEntity = (G16ParentEntity) g16ChainContext.get(ENTITY);

        final String formPropName = (String) g16ChainContext.get(ACTIONFORM_PROP_NAME);
        String aspectName = (String) g16ChainContext.get(ASPECT_NAME);
        aspectName = (StringUtils.isEmpty(aspectName) ? getGenericAspectName() : aspectName);

        Object obj = g16ChainContext.get(PARAMETER);
        Map<Integer, Object> map;
        if (obj == null)
            map = new HashMap<Integer, Object>(0);
        else
            map = (Map<Integer, Object>) g16ChainContext.get(PARAMETER);


        List<Object> args = new ArrayList<Object>(3);
        args.add(g16ParentEntity.getClass());
        args.add(map);
        args.add(aspectName);

        Object result;
        boolean resultStatus = Command.CONTINUE_PROCESSING;
        try {
            result = defaultInvokeBusiness(GenericCommandsEnum.getAllObjects, args);
            BeanUtils.setProperty(g16ChainContext.getBean(), formPropName, result);
        } catch (BusinessException e) {
            resultStatus = Command.PROCESSING_COMPLETE;
            throw e;
        } catch (BusinessInvocationException e) {
            resultStatus = Command.PROCESSING_COMPLETE;
            throw e;
        } catch (IllegalAccessException e) {
            resultStatus = Command.PROCESSING_COMPLETE;
            throw e;
        } catch (InvocationTargetException e) {
            resultStatus = Command.PROCESSING_COMPLETE;
            throw e;
        }

        return resultStatus;
    }
}
