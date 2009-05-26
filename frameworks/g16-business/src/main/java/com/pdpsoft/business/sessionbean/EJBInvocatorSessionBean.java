/**
 * This class use caching for faster access to ejb SessionBeans and avoid extra lookup.
 * It use Reflection to call given method of ejb specified with jndiName.

 * Ashnaco - G16
 * User: d_farid
 * Date: Oct 12, 2006
 * Time: 9:49:51 AM
 */
package com.pdpsoft.business.sessionbean;

import com.pdpsoft.business.BusinessException;
import com.pdpsoft.business.BusinessInvocationException;
import com.pdpsoft.hibernate.util.HibernateUtility;

import java.util.ArrayList;
import java.util.List;

public class EJBInvocatorSessionBean implements javax.ejb.SessionBean {

    private EJBInvocatorUtility ejbInvocatorUtility;

    /**
     * default empty constructor
     */
    public EJBInvocatorSessionBean() {
        //empty constructor
    }

    /**
     * {@inheritDoc}
     */
    public Object invokeSessionBean(final String jndiName, final String methodName) throws BusinessInvocationException, BusinessException {
        final List<Object> list = new ArrayList<Object>(0);
        return ejbInvocatorUtility.callEJBBusiness(jndiName, methodName, list);
    }

    /**
     * {@inheritDoc}
     */
    public Object invokeSessionBean(final String jndiName, final String methodName, final Object param) throws BusinessInvocationException, BusinessException {
        final List<Object> list = new ArrayList<Object>(1);
        list.add(param);
        return ejbInvocatorUtility.callEJBBusiness(jndiName, methodName, list);
    }

    /**
     * {@inheritDoc}
     */
    public Object invokeSessionBean(final String jndiName, final String methodName, final Object param1, final Object param2) throws BusinessInvocationException, BusinessException {
        final List<Object> list = new ArrayList<Object>(2);
        list.add(param1);
        list.add(param2);
        return ejbInvocatorUtility.callEJBBusiness(jndiName, methodName, list);
    }

    /**
     * {@inheritDoc}
     */
    public Object invokeSessionBean(final String jndiName, final String methodName, final List<Object> params) throws BusinessInvocationException, BusinessException {
        return ejbInvocatorUtility.callEJBBusiness(jndiName, methodName, params);
    }

    /**
     * {@inheritDoc}
     */
    public void destroy() {
        HibernateUtility.destroy();
    }


    public EJBInvocatorUtility getEjbInvocatorUtility() {
        return ejbInvocatorUtility;
    }

    public void setEjbInvocatorUtility(final EJBInvocatorUtility ejbInvocatorUtility) {
        this.ejbInvocatorUtility = ejbInvocatorUtility;
    }

    public void ejbCreate() throws javax.ejb.CreateException {
        ejbInvocatorUtility = EJBInvocatorUtility.getInstance();
    }

    /**
     * Default ejb method
     *
     * @param sessionContext sessionContext
     * @throws javax.ejb.EJBException
     */
    public void setSessionContext(final javax.ejb.SessionContext sessionContext) throws javax.ejb.EJBException {
        //empty implementation
    }

    /**
     * Default ejb method
     *
     * @throws javax.ejb.EJBException
     */
    public void ejbRemove() throws javax.ejb.EJBException {
        //empty implementation
    }

    /**
     * Default ejb method
     *
     * @throws javax.ejb.EJBException
     */
    public void ejbActivate() throws javax.ejb.EJBException {
        //empty implementation
    }

    /**
     * Default ejb method
     *
     * @throws javax.ejb.EJBException
     */
    public void ejbPassivate() throws javax.ejb.EJBException {
        //empty implementation
    }
}
