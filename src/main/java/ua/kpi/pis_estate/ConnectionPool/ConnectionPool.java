package ua.kpi.pis_estate.ConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool implements IConnectionPool {

    private static String url = "jdbc:mysql://localhost:3306/real_estate";
    private static String user = "root";
    private static String password = "toor";
    private static List<Connection> avaliableConnections = new ArrayList<>();
    private static List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 10;


    public ConnectionPool() {
    }

    public ConnectionPool(String url, String user, String password, List<Connection> pool) {
        ConnectionPool.url = url;
        ConnectionPool.user = user;
        ConnectionPool.password = password;
        ConnectionPool.avaliableConnections = pool;
    }

    private static Connection createConnection(String url, String user, String password)
      throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static ConnectionPool createConnectionPool() throws SQLException {
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }
        return new ConnectionPool(url, user, password, pool);
    }

    @Override
    public Connection getConnection() {
        Connection connection = avaliableConnections.remove(avaliableConnections.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        avaliableConnections.add(connection);
        return usedConnections.remove(connection);
    }
}

