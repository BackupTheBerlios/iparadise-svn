package com.pdpsoft.web.common;

import com.pdpsoft.g16.business.BusinessException;
import com.pdpsoft.g16.business.BusinessInvocationException;
import com.pdpsoft.commons.G16Exception;

import org.apache.commons.chain.Context;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by IntelliJ IDEA.
 * User: a_amini, h_shayan
 * Date: Jan 16, 2008
 * Time: 4:49:48 PM
 */
public class G16ChainExceptionHandler implements G16ChainFilter {
    final static Log LOGGER = LogFactory.getLog(G16ChainExceptionHandler.class);

    public boolean postprocess(Context context, Exception exception) {
        if (exception == null) {
            ExceptionHandler.handleMessage(999, ((G16ChainContext) context).getHttpServletRequest(), ((G16ChainContext) context).getActionMapping());
            return false;
        }
        if (exception instanceof BusinessException || exception instanceof BusinessInvocationException) {
            ExceptionHandler.handleException((G16Exception) exception, ((G16ChainContext) context).getHttpServletRequest(),
                    ((G16ChainContext) context).getActionMapping());
            LOGGER.error("Exception occuered in " + exception.getMessage());
        }
        return true;
    }

    public boolean execute(Context context) throws Exception {
        return false;
    }
}
