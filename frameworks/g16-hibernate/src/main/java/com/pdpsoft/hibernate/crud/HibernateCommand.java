package com.pdpsoft.hibernate.crud;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * @author hasha
 */
public interface HibernateCommand {
    Object execute(Session session) throws HibernateException;
}
