package com.pdpsoft.web.common;

import com.pdpsoft.g16.business.BusinessException;
import org.apache.commons.chain.Context;

/**
 * Created by IntelliJ IDEA.
 * User: a_amini & s_sadjedy
 * Date: May 3, 2008
 * Time: 11:42:09 AM
 */
public class ThrowEmptyExceptionCommand implements G16WebCommand{
    public boolean execute(Context context) throws Exception {
        throw new BusinessException("GenerateEmptyError", -1000);
    }
}
