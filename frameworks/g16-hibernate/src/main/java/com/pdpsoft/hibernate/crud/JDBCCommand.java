package com.pdpsoft.hibernate.crud;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author hasha
 */
public interface JDBCCommand {
    Object execute(Connection connection) throws SQLException;
}
