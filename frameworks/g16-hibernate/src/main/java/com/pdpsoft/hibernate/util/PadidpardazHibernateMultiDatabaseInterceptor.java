package com.pdpsoft.hibernate.util;

import com.pdpsoft.security.LoginModuleContextHolder;
import com.pdpsoft.security.SecurityContextHolder;
import org.hibernate.EmptyInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 26, 2009
 * Time: 1:30:56 PM
 */
public class PadidpardazHibernateMultiDatabaseInterceptor extends EmptyInterceptor {
    private final static Log LOG = LogFactory.getLog(PadidpardazHibernateMultiDatabaseInterceptor.class);

    @Override
    public String onPrepareStatement(String s) {
        String prepedStatement = super.onPrepareStatement(s);
        /**
         * as a rule in PDP company all tables start by having 'PDP_' or 'SEC_' 
         */
        final String pdpPrefix = "PDP_";
        final String securityRegularExp = "SEC_";

        final String selectedSchemaName = LoginModuleContextHolder.getInstance().getSelectedSchemaName();

        LOG.debug("the schema name is " + selectedSchemaName);
        LOG.debug("LoginModuleContextHolder.getDefaultSchemaName() is " + LoginModuleContextHolder.getDefaultSchemaName());

        // the login should happen
        if (SecurityContextHolder.getSecurityContextHolder().getAuthentication() == null) {
            LOG.debug("no login has been happened");
            prepedStatement = prepedStatement.replaceAll(securityRegularExp, LoginModuleContextHolder.getDefaultSchemaName() + "." + securityRegularExp);
        } else {
            // the schema should append to the query
            LOG.debug("put the schema name");

            if (!StringUtils.contains(prepedStatement, selectedSchemaName)) {
                prepedStatement = prepedStatement.replaceAll(pdpPrefix, selectedSchemaName + "." + pdpPrefix);
            }
            if (!StringUtils.contains(prepedStatement, LoginModuleContextHolder.getDefaultSchemaName())) {
                prepedStatement = prepedStatement.replaceAll(securityRegularExp, LoginModuleContextHolder.getDefaultSchemaName() + "." + securityRegularExp);
            }
            LOG.debug("prepedStatement is " + prepedStatement);
        }

        return prepedStatement;
    }
}
