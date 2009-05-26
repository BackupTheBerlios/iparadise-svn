package com.pdpsoft.g16.business.sessionbean;

import java.rmi.RemoteException;

public interface EJBInvocatorSessionHome extends javax.ejb.EJBHome {
    EJBInvocatorSession create() throws RemoteException, javax.ejb.CreateException;
}
