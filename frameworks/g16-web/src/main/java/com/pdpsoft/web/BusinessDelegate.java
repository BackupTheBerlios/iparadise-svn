package com.pdpsoft.web;

import com.pdpsoft.g16.business.BusinessException;
import com.pdpsoft.g16.business.BusinessInvocationException;
import com.pdpsoft.g16.business.sessionbean.EJBInvocatorSession;
import com.pdpsoft.g16.business.sessionbean.EJBInvocatorSessionHome;
import com.pdpsoft.commons.InitializationException;
import com.pdpsoft.commons.ResourceLocator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Properties;
import java.util.PropertyResourceBundle;

/**
 * The implementation class of delegate pattern for web modules.
 * The destination host and container of the ejbs must be specified in <code>g16-web.properties</code> file
 * which is in classpath.
 * <p/>
 * Ashnaco - G16
 * User: d_farid
 * Date: Oct 12, 2006
 * Time: 11:09:26 AM
 */
public class BusinessDelegate implements ServiceLocatorCommand {

    private static final Log logger = LogFactory.getLog(BusinessDelegate.class);

    private String host;
    private String appServer;

    private transient InitialContext initialContext;
    private EJBInvocatorSession invocatorSession;

    public static final String EJB_INVOKATOR_JNDI_NAME = "EJBInvocatorSessionEJB";

    /**
     * Return an instance of this class (Singleton pattern)
     *
     * @return an instance of this class
     */

    private static class BusinessDelegateHolder {//NOPMD
        private static ServiceLocatorCommand businessDelegate;

        static {
            try {
                PropertyResourceBundle bundle = new PropertyResourceBundle(
                        ResourceLocator.getResourceAsStream("g16-web.properties"));
                String host = bundle.getString("EJB-AS-host");
                String appServer = bundle.getString("AS_type");
                businessDelegate = new BusinessDelegate(host, appServer);
            } catch (RemoteException e) {
                throw new InitializationException("RemoteException while creating SessionBean instance ", e);
            } catch (IOException e) {
                throw new InitializationException("IOException while reading g16-web.properties file ", e);
            } catch (NamingException e) {
                throw new InitializationException("NamingException while instantiating or lookup jndiContext ", e);
            } catch (CreateException e) {
                throw new InitializationException("CreateException while creating SessionBean instance ", e);
            }
        }
    }

    public static ServiceLocatorCommand getInstance() {
        return BusinessDelegateHolder.businessDelegate;
    }

    BusinessDelegate(String host, String asType) throws NamingException, RemoteException, CreateException {
        this.host = host;
        this.appServer = asType;
        initSessionInvoker();
    }

    private void initSessionInvoker() throws NamingException, RemoteException, CreateException {
        initJndiContext(appServer);
        initEJBInvokator();
    }

    private void initJndiContext(final String appServer) throws NamingException {
        if (appServer.equalsIgnoreCase("jboss")) {
            final Properties jndiProperties = new Properties();
            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
            jndiProperties.put("java.naming.factory.url.pkgs", "org.jboss.naming.client:org.jnp.interfaces");
            jndiProperties.put(Context.PROVIDER_URL, host + ":1099");
            initialContext = new InitialContext(jndiProperties);
        } else {
            initialContext = new InitialContext();
        }
    }


    private void initEJBInvokator() throws NamingException, RemoteException, CreateException {
        final EJBInvocatorSessionHome invocatorSessionHome =
                (EJBInvocatorSessionHome) initialContext.lookup(EJB_INVOKATOR_JNDI_NAME);
        invocatorSession = invocatorSessionHome.create();
    }

    /* (non-Javadoc)
	 * @see com.pdpsoft.web.ServiceLocatorCommand#invokeBusiness(java.lang.String, java.lang.String, java.lang.Object)
	 */
    public Object invokeBusiness(final String jndiName, final String methodName, final Object param)
            throws BusinessException, BusinessInvocationException {
        try {
            return invocatorSession.invokeSessionBean(jndiName, methodName, param);
        } catch (RemoteException e) {
            try {
                initEJBInvokator();
                return invocatorSession.invokeSessionBean(jndiName, methodName, param);
            } catch (NamingException e1) {
                logger.error(e1);
                throw new BusinessInvocationException(e1);
            } catch (RemoteException e1) {
                logger.error(e1);
                throw new BusinessInvocationException(e1);
            } catch (CreateException e1) {
                logger.error(e1);
                throw new BusinessInvocationException(e1);
            }
        }
    }

    /* (non-Javadoc)
	 * @see com.pdpsoft.web.ServiceLocatorCommand#invokeBusiness(java.lang.String, java.lang.String, java.lang.Object, java.lang.Object)
	 */
    public Object invokeBusiness(final String jndiName, final String methodName, final Object param1, final Object param2)
            throws BusinessException, BusinessInvocationException {
        try {
            return invocatorSession.invokeSessionBean(jndiName, methodName, param1, param2);
        } catch (RemoteException e) {
            try {
                initEJBInvokator();
                return invocatorSession.invokeSessionBean(jndiName, methodName, param1, param2);
            } catch (NamingException e1) {
                logger.error(e1);
                throw new BusinessInvocationException(e1);
            } catch (RemoteException e1) {
                logger.error(e1);
                throw new BusinessInvocationException(e1);
            } catch (CreateException e1) {
                logger.error(e1);
                throw new BusinessInvocationException(e1);
            }
        }
    }

    /* (non-Javadoc)
	 * @see com.pdpsoft.web.ServiceLocatorCommand#invokeBusiness(java.lang.String, java.lang.String, java.util.List)
	 */
    public Object invokeBusiness(final String jndiName, final String methodName, final List<Object> params)
            throws BusinessException, BusinessInvocationException {
        try {
            return invocatorSession.invokeSessionBean(jndiName, methodName, params);
        } catch (RemoteException e) {
            try {
                initEJBInvokator();
                return invocatorSession.invokeSessionBean(jndiName, methodName, params);
            } catch (NamingException e1) {
                logger.error(e1);
                throw new BusinessInvocationException(e1);
            } catch (RemoteException e1) {
                logger.error(e1);
                throw new BusinessInvocationException(e1);
            } catch (CreateException e1) {
                logger.error(e1);
                throw new BusinessInvocationException(e1);
            }
        }
    }

    /**
     * Destroy the connections to database.
     */
    public void destroy() {
        try {
            invocatorSession.destroy();
        } catch (RemoteException e) {
            logger.error("RemoteException while invoking destroy of remote InvocatorSession EJB", e);
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public String getAppServer() {
        return appServer;
    }

    public void setAppServer(final String appServer) {
        this.appServer = appServer;
    }

    public EJBInvocatorSession getInvocatorSession() {
        return invocatorSession;
    }

    public void setInvocatorSession(final EJBInvocatorSession invocatorSession) {
        this.invocatorSession = invocatorSession;
    }
}
