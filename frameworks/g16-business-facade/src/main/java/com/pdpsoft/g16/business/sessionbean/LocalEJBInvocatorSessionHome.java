package com.pdpsoft.g16.business.sessionbean;

public interface LocalEJBInvocatorSessionHome extends javax.ejb.EJBLocalHome {
    LocalEJBInvocatorSession create() throws javax.ejb.CreateException;
}
