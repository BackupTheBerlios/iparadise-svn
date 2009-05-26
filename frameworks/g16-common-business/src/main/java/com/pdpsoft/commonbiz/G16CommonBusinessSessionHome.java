/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Dec 20, 2006
 * Time: 1:09:46 PM
 */
package com.pdpsoft.commonbiz;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;

public interface G16CommonBusinessSessionHome extends EJBHome {
    G16CommonBusinessSession create() throws RemoteException, CreateException;
}
