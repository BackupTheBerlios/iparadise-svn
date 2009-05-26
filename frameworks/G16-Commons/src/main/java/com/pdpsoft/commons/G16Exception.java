package com.pdpsoft.commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Jul 15, 2006
 * Time: 11:05:53 AM
 * Description: This class is the base of all exceptions.Which
 * the logging procedures should implement on it.
 */
public class G16Exception extends Exception implements Serializable {

    private static final long serialVersionUID = 348732487854783423L;

    private static final transient Log log = LogFactory.getLog(G16Exception.class);

    private int errorNumber;

    /**
     * @param message
     */
    public G16Exception(String message) {
        super(message);
        log.error(message);
    }

    /**
     * @param message
     * @param cause
     */
    public G16Exception(String message, Throwable cause) {
        super(message, cause);
        log.error(message, cause);
    }

    /**
     * @param cause
     */
    public G16Exception(Throwable cause) {
        super(cause);
        log.error(cause);
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     * @param errorNumber error number
     */
    public G16Exception(String message, int errorNumber) {
        super(message);
        this.errorNumber = errorNumber;
        log.error("Error Number: " + errorNumber + " - " + message);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * <code>cause</code> is <i>not</i> automatically incorporated in
     * this exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.4
     * @param errorNumber error number
     */
    public G16Exception(String message, Throwable cause, int errorNumber) {
        super(message, cause);
        this.errorNumber = errorNumber;
        log.error("Error Number: " + errorNumber + " - " + message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of <tt>(cause==null ? null : cause.toString())</tt> (which
     * typically contains the class and detail message of <tt>cause</tt>).
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables (for example, {@link
     * java.security.PrivilegedActionException}).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A <tt>null</tt> value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @since 1.4
     * @param errorNumber error number
     */
    public G16Exception(Throwable cause, int errorNumber) {
        super(cause);
        this.errorNumber = errorNumber;
        log.error("Error Number: " + errorNumber, cause);
    }

    public int getErrorNumber() {
        return errorNumber;
    }
}
