package com.pdpsoft.web.common.commands;

import com.pdpsoft.g16.business.BusinessException;
import com.pdpsoft.g16.business.BusinessInvocationException;
import com.pdpsoft.commons.G16ParentEntity;
import com.pdpsoft.web.common.G16ChainContext;
import com.pdpsoft.web.common.G16WebCommand;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.Command;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: h_shayan, a_heydari
 * Date: 04-Jun-2008
 * Time: 15:50:11
 */
public class PersistWebCommand extends G16GenericWebCommand implements G16WebCommand {
    public boolean execute(Context context) throws Exception {
        G16ChainContext g16ChainContext = (G16ChainContext) context;

        G16ParentEntity g16ParentEntity = (G16ParentEntity) g16ChainContext.get(ENTITY);

        String aspectName = (String) g16ChainContext.get(ASPECT_NAME);
        aspectName = (StringUtils.isEmpty(aspectName) ? getGenericAspectName() : aspectName);

        List<Object> args = new ArrayList<Object>(2);
        args.add(g16ParentEntity);
        args.add(aspectName);

        boolean resultStatus = Command.CONTINUE_PROCESSING;
        try {
            defaultInvokeBusiness(GenericCommandsEnum.persistObject, args);
        } catch (BusinessException e) {
            resultStatus = Command.PROCESSING_COMPLETE;
            throw e;
        } catch (BusinessInvocationException e) {
            resultStatus = Command.PROCESSING_COMPLETE;
            throw e;
        }
        return resultStatus;
    }
}
