package ua.kpi.pis_estate.ConnectionPool;

import java.sql.Connection;

public interface IConnectionPool {
    Connection getConnection();
    boolean releaseConnection(Connection connection);
}
