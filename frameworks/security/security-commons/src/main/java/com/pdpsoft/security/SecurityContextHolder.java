package com.pdpsoft.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 4, 2009
 * Time: 9:07:04 AM
 * Associates a given SecurityContext with the current execution thread.
 */
public class SecurityContextHolder implements SecurityContext {
    private static final Log LOG = LogFactory.getLog(SecurityContextHolder.class);
    /*
        This is the ThreadLocal Pattern that it should keep the SecurityContext
     */
    private static ThreadLocal<SystemUserEntity> systemUserEntityThreadLocal = new ThreadLocal<SystemUserEntity>();
    private static ThreadLocal<List<SystemActionEntity>> actionsThreadLocal = new ThreadLocal<List<SystemActionEntity>>();
    private static SecurityContextHolder context = new SecurityContextHolder();

    private int instiationCounter = 0;
    private SecurityContextHolder() {
        instiationCounter++;
    }

    /**
     * Returns all user information
     * @return All information of user in AuthorizationCustomEntity type.
     */
    public AuthorizationCustomEntity getAllUserInformation() {
        return new AuthorizationCustomEntity(
                getAuthentication(),
                getAuthorizations()
        );
    }

    /**
     * Obtains the currently authenticated principal, or an authentication request token.
     *
     * @return the Authentication or null if no authentication information is available
     */
    public SystemUserEntity getAuthentication() {
        SystemUserEntity context = systemUserEntityThreadLocal.get();
        if(context != null)
            LOG.debug("SecurityContext of user ".concat(context.getUserName()).concat(" has been gotten."));
        return context;
    }

    /**
     * Obtains the user authorizations, which it means the whole accesses that he has.
     *
     * @return List of SystemActionEntity that the current user has them.
     */
    public List<SystemActionEntity> getAuthorizations() {
        List<SystemActionEntity> context = actionsThreadLocal.get();
        LOG.debug("Actions has been gotten.");
        return context;
    }

    /**
     * Changes the currently authenticated principal, or removes the authentication information.
     *
     * @param authentication the new Authentication token, or null if no further authentication information should be stored
     */
    public void setAuthentication(AuthorizationCustomEntity authentication) {
        if(authentication != null)
            LOG.debug("SecurityContext of user ".concat(authentication.getSystemUserEntity().getUserName()).concat(" is registering ..."));
        assert authentication != null;
        systemUserEntityThreadLocal.set(authentication.getSystemUserEntity());
        actionsThreadLocal.set(authentication.getSystemActionEntities());
        LOG.debug("SecurityContext of user ".concat(authentication.getSystemUserEntity().getUserName()).concat(" is registered successfully."));
    }

    /**
     * Explicitly clears the context value from the current thread.
     */
    public static void clearContext() {
        systemUserEntityThreadLocal.set(null);
        actionsThreadLocal.set(null);
    }

    /**
     * Obtain the current SecurityContext.
     *
     * @return the security context (never null)
     */
    public static SecurityContext getContext() {
        return context;
    }

    /**
     * Obtain the current SecurityContext.
     *
     * @return the security context (never null)
     */
    public static SecurityContextHolder getSecurityContextHolder() {
        return context;
    }

    /**
     * Primarily for troubleshooting purposes, this method shows how many times the class has reinitialized
     *
     * @return the count
     */
    public int getInitializeCount() {
        return instiationCounter--;
    }

    /**
     * Called by the garbage collector on an object when garbage collection
     * determines that there are no more references to the object.
     * A subclass overrides the <code>finalize</code> method to dispose of
     * system resources or to perform other cleanup.
     * <p/>
     * The general contract of <tt>finalize</tt> is that it is invoked
     * if and when the Java<font size="-2"><sup>TM</sup></font> virtual
     * machine has determined that there is no longer any
     * means by which this object can be accessed by any thread that has
     * not yet died, except as a result of an action taken by the
     * finalization of some other object or class which is ready to be
     * finalized. The <tt>finalize</tt> method may take any action, including
     * making this object available again to other threads; the usual purpose
     * of <tt>finalize</tt>, however, is to perform cleanup actions before
     * the object is irrevocably discarded. For example, the finalize method
     * for an object that represents an input/output connection might perform
     * explicit I/O transactions to break the connection before the object is
     * permanently discarded.
     * <p/>
     * The <tt>finalize</tt> method of class <tt>Object</tt> performs no
     * special action; it simply returns normally. Subclasses of
     * <tt>Object</tt> may override this definition.
     * <p/>
     * The Java programming language does not guarantee which thread will
     * invoke the <tt>finalize</tt> method for any given object. It is
     * guaranteed, however, that the thread that invokes finalize will not
     * be holding any user-visible synchronization locks when finalize is
     * invoked. If an uncaught exception is thrown by the finalize method,
     * the exception is ignored and finalization of that object terminates.
     * <p/>
     * After the <tt>finalize</tt> method has been invoked for an object, no
     * further action is taken until the Java virtual machine has again
     * determined that there is no longer any means by which this object can
     * be accessed by any thread that has not yet died, including possible
     * actions by other objects or classes which are ready to be finalized,
     * at which point the object may be discarded.
     * <p/>
     * The <tt>finalize</tt> method is never invoked more than once by a Java
     * virtual machine for any given object.
     * <p/>
     * Any exception thrown by the <code>finalize</code> method causes
     * the finalization of this object to be halted, but is otherwise
     * ignored.
     *
     * @throws Throwable the <code>Exception</code> raised by this method
     */
    @Override
    protected void finalize() throws Throwable {
        instiationCounter--;
    }
}
