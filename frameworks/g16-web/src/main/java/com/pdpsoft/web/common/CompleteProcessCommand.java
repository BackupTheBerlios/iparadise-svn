package com.pdpsoft.web.common;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * Created by IntelliJ IDEA.
 * User: a_amini & s_sadjedy
 * Date: May 3, 2008
 * Time: 11:42:09 AM
 */
public class CompleteProcessCommand implements G16WebCommand{
    public boolean execute(Context context) throws Exception {
        return Command.PROCESSING_COMPLETE;
    }
}
